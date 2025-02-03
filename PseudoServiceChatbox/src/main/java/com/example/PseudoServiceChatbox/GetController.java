package com.example.PseudoServiceChatbox;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GetController {

    @GetMapping("/config")
    public String getConfig() {
        String value = Config.getValue();

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Configuration actuelle</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n<div>La configuration actuelle est déduite de la chaîne " + value +
                "</body>\n" +
                "</html>";
    }
}
