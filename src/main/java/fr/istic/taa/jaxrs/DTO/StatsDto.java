package fr.istic.taa.jaxrs.DTO;

public class StatsDto {
    public long ticketsSold;
    public double revenue;
    public int capacity;

    public StatsDto(long ticketsSold, double revenue, int capacity) {
        this.ticketsSold = ticketsSold;
        this.revenue = revenue;
        this.capacity = capacity;
    }
}
