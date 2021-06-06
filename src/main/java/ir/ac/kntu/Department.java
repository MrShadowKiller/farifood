package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;

public abstract class Department {
    private String name;

    private Address address;

    private int workHoursOpen;

    private int workHoursClose;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Order> orders;

    private ArrayList<Comment> comments;

    private ArrayList<Item> items;

    public Department(String name, Address address, int workHoursOpen, int workHoursClose) {
        this.name = name;
        this.address = address;
        this.workHoursOpen = workHoursOpen;
        this.workHoursClose = workHoursClose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getWorkHoursOpen() {
        return workHoursOpen;
    }

    public void setWorkHoursOpen(int workHoursOpen) {
        this.workHoursOpen = workHoursOpen;
    }

    public int getWorkHoursClose() {
        return workHoursClose;
    }

    public void setWorkHoursClose(int workHoursClose) {
        this.workHoursClose = workHoursClose;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getAverageRate() {
        if (comments.size() == 0) {
            return 5;
        }
        double averageRate = 0;
        for (Comment comment : comments) {
            averageRate += comment.getAverageRate();
        }
        return averageRate / comments.size();
    }
}
