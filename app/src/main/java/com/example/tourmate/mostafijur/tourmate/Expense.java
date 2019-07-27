package com.example.tourmate.mostafijur.tourmate;

public class Expense {
    private String eventId;
    private String eexpenseName;
    private double expenseAmount;

    public Expense() {

    }

    public Expense(String eventId, String eexpenseName, double expenseAmount) {
        this.eventId = eventId;
        this.eexpenseName = eexpenseName;
        this.expenseAmount = expenseAmount;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEexpenseName() {
        return eexpenseName;
    }

    public void setEexpenseName(String eexpenseName) {
        this.eexpenseName = eexpenseName;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
