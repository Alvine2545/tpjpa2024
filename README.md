# Template de projet pour le TP JPA 2021 UniR
## Comment lancer
    1 Lancer le serveur (le fichier RestServer)
    2 Lancer la base de donnée ( show-hsqldb.sh et run-hsqldb-server.sh )
    3 Base de donnée de l'ISTIC
    4 Utilisation de POSTMAN pour les requêtes
## Modèle métier
Le modèle métier se trouve dans le fichier tickettingDiagram.drawio.png à la raccine du projet. 

    2 User
    2 Role
    3 Concert
    3 Ticket
    4 Notification

-   Relation entre en les classes métiers 
User - Role : ManyToMany
User - Ticket
Ticket - Concert
Concert- User
Ticket: Abstract class (Pas de création d'objet de cette classe). Classe mère de TicketStandard, TicketLastMinute et TicketPremium
- La première partie portant sur l'utilisation de Servlet s trouve sur la branche master
- La partie final incluant l'utiliastion de JaxRS et OpenAPI se trouve sur la branche main

## Ce qui marche
- Sur la branche master se trouve la première partie du TP avec l'utilisation de jetty. 
- Sur la branche main se trouve la deuxième partie avec l'utilisation de JaxRS et OpenAI.
- L'insersion dans la base de donnée
- 

## Ce qui ne marche pas
- Tous les liens retournent une erreur 404
- L'héritage entre les différents type de tickets


Pour des raisons de simplicité, on aurait pu changé l'héritage des tickets
