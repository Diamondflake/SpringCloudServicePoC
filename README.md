Le but de cette P.o.C. est de prouver que l’utilisation de Spring Cloud Config est viable pour la mise à jour à chaud de la configuration I.A. même sans Bus de Données (qui est la solution standard pour la mise à jour à chaud).

Cette P.o.C. modèle 3 applications qui représentent le comportement voulu dans l’application Ollca:

  - Micro-Service Spring Cloud Config, qui aide tout service le contactant à se mettre à jour auprès d’un dépôt Git (dans ce cas, un dépôt Github).

  - Pseudo-Micro-Service Chatbot, qui se met à jour auprès du M.S. Spring Cloud Config à son lancement et lorsqu’il reçoit un signal HTML spécifique.

  - Pseudo-Micro-Service Studio, qui permet de mettre à jour le dépôt Git puis signale au P.M.S. Chatbot de se mettre à jour.

Des endpoints et pages primitives permettent de confirmer ces comportements de manière aisée.

Afin de faire fonctionner cette P.o.C., il est nécessaire de pouvoir compiler et lancer des applications Java Spring Boot.

Par défaut, les parties Studio, Chatbot, et Spring Cloud Config utilisent respectivement les ports 7070, 8080, et 8888. Si ces ports ne conviennent pas, ils doivent être changés dans les application.properties des applications concernées.

La P.o.C. utilise par défaut un token (qui expire le 28 Février 2025) pour pouvoir éditer un dépôt Github public en le clonant temporairement localement; Il est préférable de changer le chemin de la copie locale pour ne pas créer toute la chaîne de dossiers. Celui-ci et le dépôt Git peuvent être changé de la application.properties de la partie Studio.

Une fois les application.properties correctement édités s’ils doivent l’être, lancer la partie Cloud Config Service (ServiceSpringCloudConfig).

Une fois celle-ci lancée, lancer la partie Chatbot (PseudoServiceChatbox) et accéder à la page de consultation de la configuration /config (par défaut, http://localhost:8080/config) pour vérifier que la configuration a bien été changée de sa valeur par défaut (en effet, le application.properties de la partie Chatbot met normalement la valeur à “Pas mise à jour”; Si elle est différente, c’est que la mise à jour au démarrage fonctionne bien).

Ensuite, lancer la partie Studio (PseudoServiceStudio) et accéder à la page de mise à jour de la configuration /form.html (par défaut, http://localhost:7070/form.html) pour tester la mise à jour. Il suffit d’insérer du texte, appuyer sur “Submit query”, et attendre un instant que la mise à jour se fasse; Ensuite, la valeur est consultable comme dans le paragraphe précédent et devrait avoir changé.
Problèmes Connus
Le Pseudo-Micro-Service Studio lance une exception “Access Denied” mais parvient malgré tout correctement à créer, modifier, puis effacer ses fichiers. L’exception n’a donc à priori pas d’effet averse au fonctionnement.

L’affichage des caractères UTF-8 se fait mal du côté Chatbox (ils sont cependant bien stockés dans le Git).
