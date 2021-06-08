package ir.ac.kntu.models.departments;

import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.objects.*;
import ir.ac.kntu.models.order.Comment;
import ir.ac.kntu.models.order.Order;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.Customer;
import ir.ac.kntu.models.user.SellerMan;

import java.util.ArrayList;


public abstract class Department {
    private String name;

    private Address address;

    private SellerMan sellerMan;

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

    public SellerMan getSellerMan() {
        return sellerMan;
    }

    public void setSellerMan(SellerMan sellerMan) {
        this.sellerMan = sellerMan;
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

    public void removeDelivery(Delivery delivery) {
        if (delivery != null) {
            deliveries.remove(delivery);
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


    public abstract void getDepartmentItemMenu(ViewPerson viewPerson);

    public abstract Order checkOutCustomerOrder(Customer customer);

    public void sortItemHighRating() {
        items.sort((o1, o2) -> Double.compare(o2.getAverageRate(), o1.getAverageRate()));
    }

    public void sortItemLowRating() {
        items.sort((o1, o2) -> Double.compare(o1.getAverageRate(), o2.getAverageRate()));
    }

    public void sortItemHighPrice() {
        items.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
    }

    public void sortItemLowPrice() {
        items.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
    }

    @Override
    public String toString() {
        return getName() + "\t" + getAverageRate() + "\t";
    }
}
