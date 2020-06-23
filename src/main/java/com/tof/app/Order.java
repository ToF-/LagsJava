package com.tof.app;

public class Order implements Comparable<Order> {
    private String id;
    private int startDate;
    private int duration;
    private double price;

    public Order(String id, int startDate, int duration, double price) {
        this.id = id;
        this.startDate = startDate;  // au format AAAAJJJ par exemple 25 février 2015 = 2015056
        this.duration = duration;
        this.price = price;
    }

    public String getId() {
        return this.id;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public int getDuration() {
        return this.duration;
    }

    public double getPrice() {
        return this.price;
    }

    public int compareTo(Order order) {
        return this.startDate - order.getStartDate();
    }

}
