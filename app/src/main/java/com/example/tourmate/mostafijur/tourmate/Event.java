package com.example.tourmate.mostafijur.tourmate;

public class Event {
    private String eventId;
    private String eventName;
    private String createDate;
    private String startDate;
    private double budget;

    public Event() {

    }

    public Event(String eventId, String eventName, String createDate, String startDate, double budget) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.createDate = createDate;
        this.startDate = startDate;
        this.budget = budget;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
