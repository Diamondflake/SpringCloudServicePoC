package com.example.PseudoServiceStudio;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@RestController
public class StudioController {

    static boolean delete(File file) {
        File[] subfiles = file.listFiles();

        boolean done = true;

        if (subfiles != null) {
            for (File subfile : subfiles) {
                if (delete(subfile) == false) {
                    done = false;
                    System.out.println("Error deleting subfile " + subfile.toString());
                }
            }
        }
        return (file.delete() && done);
    }

    @PostMapping("/config")
    public static String updateConfig(@RequestParam("config") String config) throws IOException, GitAPIException {
        System.out.println("Received config: " + config);

        String line = "AIConfig.value=" + config;

        String gitLocalPath = Config.getGitLocalPath();
        String gitURL = Config.getGitURL();
        String gitToken = Config.getGitToken();

        File directory = new File(gitLocalPath);

        if (directory.exists()) {
            System.out.println(delete(directory));
        }

        Git git = Git.cloneRepository()
                .setURI(gitURL)
                .setDirectory(directory)
                .call();

        directory.createNewFile();

        File configFile = new File(gitLocalPath + "/PseudoServiceChatbox.properties");

        BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));

        writer.write(line);
        writer.close();

        git.add().addFilepattern(".").call();

        CommitCommand commit = git.commit();
        commit.setMessage("Production AI configuration update").call();

        PushCommand pushCommand = git.push();
        pushCommand.setRemote(gitURL);
        pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitToken, ""));
        pushCommand.call();

        git.rm();
        git.close();

        delete(directory);

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080/actuator/refresh");

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        return "Done";
    }
}
