package ir.ac.kntu.models.order;

import ir.ac.kntu.models.departments.Department;
import ir.ac.kntu.models.objects.Item;
import ir.ac.kntu.models.user.UserRate;
import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.user.Customer;

import java.util.ArrayList;
import java.util.Objects;

public class Comment {
    private Customer customer;

    private final ArrayList<Item> items;

    private final Department department;

    private Delivery delivery;

    private UserRate foodRate;

    private UserRate deliveryRate;

    private UserRate restaurantRate;

    private String message;

    public Comment(Customer customer, ArrayList<Item> items, Department department, Delivery delivery, UserRate foodRate,
                   UserRate deliveryRate, UserRate restaurantRate, String message) {
        this.customer = customer;
        this.items = items;
        this.department = department;
        this.delivery = delivery;
        this.foodRate = foodRate;
        this.deliveryRate = deliveryRate;
        this.restaurantRate = restaurantRate;
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Department getDepartment() {
        return department;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public UserRate getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(UserRate foodRate) {
        this.foodRate = foodRate;
    }

    public UserRate getDeliveryRate() {
        return deliveryRate;
    }

    public void setDeliveryRate(UserRate deliveryRate) {
        this.deliveryRate = deliveryRate;
    }

    public UserRate getRestaurantRate() {
        return restaurantRate;
    }

    public void setRestaurantRate(UserRate restaurantRate) {
        this.restaurantRate = restaurantRate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAverageRate() {
        return (restaurantRate.getRate() * 3 + foodRate.getRate() * 2 +
                deliveryRate.getRate()) / 6.0;
    }

    public String toString() {
        String result = "";
        for (Item item : items) {
            result += item.getName() + " : " + item.getPrice() + "$\n\t";
        }

        return "Customer Details :\n\t" + customer.briefInformation() +
                "\nDepartment : \n\t" + department.getName() +
                "\nItems :\n\t" + result +
                "\nDelivery : \n\t" + delivery.getBriefInformation() +
                "\nMessage : \n\t" + message + "\nFood rate : " + foodRate +
                "\tDelivery Rate : " + deliveryRate + "\tRestaurant Rate : " + restaurantRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return customer.equals(comment.customer) && items.equals(comment.items) &&
                department.equals(comment.department) && delivery.equals(comment.delivery)
                && foodRate == comment.foodRate && deliveryRate == comment.deliveryRate &&
                restaurantRate == comment.restaurantRate && message.equals(comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, items, department, delivery, foodRate,
                deliveryRate, restaurantRate, message);
    }
}
