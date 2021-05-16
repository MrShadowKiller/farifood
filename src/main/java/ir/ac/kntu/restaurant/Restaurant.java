package ir.ac.kntu.restaurant;

import ir.ac.kntu.Address;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.Food;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Restaurant {
    private String name;

    private Address address;

    private String workHoursOpen;

    private String workHoursClose;

    private RestaurantSchedule[] schedule;

    private RestaurantType restaurantType;

    private ArrayList<Food> foods;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Order> orders;

    private ArrayList<Comment> comments;


    public Restaurant(String name, Address address, String workHoursOpen, String workHoursClose,
                      RestaurantSchedule[] schedule, RestaurantType restaurantType,
                      ArrayList<Food> foods) {
        this.name = name;
        this.address = address;
        this.workHoursOpen = workHoursOpen;
        this.workHoursClose = workHoursClose;
        this.schedule = schedule;
        this.restaurantType = restaurantType;
        this.foods = foods;
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

    public String getWorkHoursOpen() {
        return workHoursOpen;
    }

    public void setWorkHoursOpen(String workHoursOpen) {
        this.workHoursOpen = workHoursOpen;
    }

    public String getWorkHoursClose() {
        return workHoursClose;
    }

    public void setWorkHoursClose(String workHoursClose) {
        this.workHoursClose = workHoursClose;
    }

    public RestaurantSchedule[] getSchedule() {
        return schedule;
    }

    public void setSchedule(RestaurantSchedule[] schedule) {
        this.schedule = schedule;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public double getAverageRate() {
        double averageRate = 0;
        for (Comment comment : comments) {
            averageRate += comment.getAverageRate();
        }
        return averageRate / comments.size();
    }

    public void setDayAvailable(RestaurantSchedule day) {
        for (RestaurantSchedule d : schedule) {
            if (d == day) {
                d.setAvailability(true);
            }
        }
    }

    public boolean isOpen(RestaurantSchedule day) {
        for (RestaurantSchedule d : schedule) {
            if (d == day && d.getAvailability()) {
                return true;
            }
        }
        return false;
    }

    public void sortFoodHighRating() {
        for (int i = 0; i <= foods.size(); i++) {
            for (int j = i + 1; j <= foods.size(); j++) {
                if (foods.get(i).getAverageRate() < foods.get(j).getAverageRate()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    public void sortFoodLowRating() {
        for (int i = 0; i <= foods.size(); i++) {
            for (int j = i + 1; j <= foods.size(); j++) {
                if (foods.get(i).getAverageRate() > foods.get(j).getAverageRate()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    public void sortFoodHighPrice() {
        for (int i = 0; i <= foods.size(); i++) {
            for (int j = i + 1; j <= foods.size(); j++) {
                if (foods.get(i).getPrice() < foods.get(j).getPrice()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    public void sortFoodLowPrice() {
        for (int i = 0; i <= foods.size(); i++) {
            for (int j = i + 1; j <= foods.size(); j++) {
                if (foods.get(i).getPrice() > foods.get(j).getPrice()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return name.equals(that.name) && address.equals(that.address) &&
                restaurantType == that.restaurantType && foods.equals(that.foods);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, address, restaurantType, foods);
    }
}
