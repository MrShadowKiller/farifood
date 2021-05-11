package ir.ac.kntu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private Customer customer;

    private Restaurant restaurant;

    private Food food;

    private Delivery delivery;

    private WeekDays day;

    private LocalDateTime dateTime;

    public Order(Customer customer, Restaurant restaurant, Food food,
                 Delivery delivery, WeekDays day, LocalDateTime dateTime) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.food = food;
        this.delivery = delivery;
        this.day = day;
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

    public WeekDays getDay() {
        return day;
    }

    public void setDay(WeekDays day) {
        this.day = day;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
