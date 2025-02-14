# Template de projet pour le TP JPA 2021 UniR
## Comment lancer
    1 Lancer le serveur
    2 Lancer la base de donnée
    3 Il s'agit de la base de donnée de l'ISTIC
    4 Utilisation de POSTMAN
## Modèle métier
    1   User
    2   Role
    3   Concert
    3   Tickets
-   Relation entre en les classes métiers 
User - Role : ManyToMany
User - Ticket
Ticket - Concert
Concert- User
Ticket: Abstract class (Pas de création d'objet de cette classe). Classe mère de TicketStandard, TicketLastMinute et TicketPremium
- La première partie portant sur l'utilisation de Servlet s trouve sur la branche master
- La partie final incluant l'utiliastion de JaxRS et OpenAPI se trouve sur la branche main
- 

