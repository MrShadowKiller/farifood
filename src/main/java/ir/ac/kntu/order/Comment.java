package ir.ac.kntu.order;

import ir.ac.kntu.Food;
import ir.ac.kntu.UserRate;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Customer;

import java.util.Objects;

public class Comment {
    private Customer customer;

    private Food food;

    private Restaurant restaurant;

    private Delivery delivery;

    private UserRate foodRate;

    private UserRate deliveryRate;

    private UserRate restaurantRate;

    private String message;

    public Comment(Customer customer, Food food, Restaurant restaurant, Delivery delivery, UserRate foodRate,
                   UserRate deliveryRate, UserRate restaurantRate, String message) {
        this.customer = customer;
        this.food = food;
        this.restaurant = restaurant;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public double getAverageRate(){
        return (restaurantRate.getRate() * 3 + foodRate.getRate() * 2 +
                deliveryRate.getRate())/6;
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
        return customer.equals(comment.customer) && food.equals(comment.food) &&
                restaurant.equals(comment.restaurant) && delivery.equals(comment.delivery)
                && foodRate == comment.foodRate && deliveryRate == comment.deliveryRate &&
                restaurantRate == comment.restaurantRate && message.equals(comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, food, restaurant, delivery, foodRate,
                deliveryRate, restaurantRate, message);
    }
}
