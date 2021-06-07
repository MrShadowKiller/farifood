package ir.ac.kntu.order;

import ir.ac.kntu.Department;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.user.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private Customer customer;

    private Department department;

    private ArrayList<Item> items;

    private Delivery delivery;

    private Comment comment;

    private OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

    private LocalDateTime dateTime;


    public Order(Customer customer, Department department,ArrayList<Item> items,
                 Delivery delivery, LocalDateTime dateTime) {
        this.customer = customer;
        this.department = department;
        this.items = items;
        this.delivery = delivery;
        this.dateTime = dateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getOrderCost(){
        double cost = 0;
        for (Item item : items){
            cost += item.getPrice();
        }
        return cost;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateTime.format(dateformat);
        String result = "";
        for (Item item : items){
            result += item.getName() + " : " + item.getPrice() + "$\n\t";
        }
        return "Customer Details :\n\t" + customer.briefInformation() +
                "\nRestaurant : \n\t" + department.getName() +
                "\nItems :\n\t" + result +
                "\nDelivery : \n\t" + delivery.getBriefInformation() +
                "\n Time : \n\t" + formattedDate;
    }
}
