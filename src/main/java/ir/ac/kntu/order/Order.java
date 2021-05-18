package ir.ac.kntu.order;

import ir.ac.kntu.Food;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.user.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Customer customer;

    private Restaurant restaurant;

    private Food food;

    private Delivery delivery;

    private Comment comment;

    private OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

    private LocalDateTime dateTime;



    public Order(Customer customer, Restaurant restaurant, Food food,
                 Delivery delivery,  LocalDateTime dateTime) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.food = food;
        this.delivery = delivery;
        this.dateTime = dateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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

        return "Customer Details :\n\t" + customer.briefInformation() +
                "\nRestaurant : \n\t" + restaurant.getName()  +
                "\nFood :\n\t" + food.getName() + food.getPrice() +
                "\nDelivery : \n\t" + delivery.getBriefInformation() +
                "\n Time : \n\t" + formattedDate;
    }
}
