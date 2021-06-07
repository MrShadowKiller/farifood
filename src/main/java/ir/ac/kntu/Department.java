package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.*;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.sort.ItemCompareHighPrice;
import ir.ac.kntu.sort.ItemCompareHighRate;
import ir.ac.kntu.sort.ItemCompareLowPrice;
import ir.ac.kntu.sort.ItemCompareLowRate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        items = new ArrayList<>();
        deliveries = new ArrayList<>();
        orders = new ArrayList<>();
        comments = new ArrayList<>();
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


//    public ArrayList<Fruit> getFruits(){
//        ArrayList<Fruit> fruits = new ArrayList<>();
//        for (Item item : items ) {
//            if (item instanceof Fruit){
//                fruits.add((Fruit) item);
//            }
//        }
//        return fruits;
//    }
//

    public void addItem(Item item) {
        items.remove(item);
        if (item != null) {
            items.add(item);
        }
    }

    public void addDelivery(Delivery delivery) {
        if (delivery != null && !deliveries.contains(delivery)) {
            deliveries.add(delivery);
        }
    }

    public void addComment(Comment comment) {
        if (comment != null) {
            comments.add(comment);
        }

    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public void sortItemHighRating() {
        items.sort(new ItemCompareHighRate());
    }

    public void sortItemLowRating() {
        items.sort(new ItemCompareLowRate());
    }

    public void sortItemHighPrice() {
        items.sort(new ItemCompareHighPrice());
    }

    public void sortItemLowPrice() {
        items.sort(new ItemCompareLowPrice());
    }
}
