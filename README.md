# Template de projet pour le TP JPA 2021 UniR
## Modèle métier
    1   User
    2   Role
    3   Concert
    3   Tickets
User - Role : ManyToMany
User - Ticket
Ticket - Concert
Concert- User
Ticket: Abstract class (Pas de création d'objet de cette classe). Classe mère de TicketStandard, TicketLastMinute et TicketPremium

