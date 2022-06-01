package vsu.by.models;

import java.util.Date;

/**
 * класс рейса
 *
 * @author Kovzov Denis
 * @see vsu.by.models.Entity
 */
public class Flight extends Entity {
    private Integer numberTickets;
    private Date departureTime;
    private Date arrivalTime;
    private String departurePoint;
    private String destination;
    private Status status;
    private Brigade brigade;

    public Integer getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(Integer numberTickets) {
        this.numberTickets = numberTickets;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

}
