package ir.ac.kntu.restaurant;

import ir.ac.kntu.objects.Address;
import ir.ac.kntu.user.WeekDays;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.setting.UserSetting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Restaurant {
    private String name;

    private Address address;

    private int workHoursOpen;

    private int workHoursClose;

    private RestaurantSchedule[] schedule;

    private RestaurantType restaurantType;

    private ArrayList<Food> foods;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Order> orders;

    private ArrayList<Comment> comments;


    public Restaurant(String name, Address address, int workHoursOpen, int workHoursClose,
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

    public Restaurant(String name, Address address, int workHoursOpen, int workHoursClose,
                      RestaurantSchedule[] schedule, RestaurantType restaurantType) {
        this.name = name;
        this.address = address;
        this.workHoursOpen = workHoursOpen;
        this.workHoursClose = workHoursClose;
        this.schedule = schedule;
        this.restaurantType = restaurantType;
        foods = new ArrayList<>();
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
        foods.remove(food);
        if (food != null) {
            foods.add(food);
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

    public void setDayAvailable(RestaurantSchedule day) {
        for (RestaurantSchedule d : schedule) {
            if (d == day) {
                d.setAvailability(true);
            }
        }
    }

    public boolean isOpen(UserSetting userSetting) {
        RestaurantSchedule restaurantSchedule = RestaurantSchedule.DEFAULT;
        restaurantSchedule = restaurantSchedule.findTheSameDay(userSetting.getCurrentDay());
        for (RestaurantSchedule day : schedule) {
            if (day == restaurantSchedule && day.getAvailability()) {
                if (userSetting.getCurrentHour() >= workHoursOpen && userSetting.getCurrentHour() <= workHoursClose) {
                    return true;
                }
            }
        }
        return false;
    }

    public Delivery getFreeDelivery(WeekDays day) {
        for (Delivery delivery : deliveries) {
            for (DeliverySchedule deliverySchedule : delivery.getSchedule()) {
                if (deliverySchedule.getRestaurant() == this) {
                    return delivery;
                }
            }
        }
        return null;
    }

    public void sortFoodHighRating() {
        foods.sort((x, y) -> (int) (Math.ceil(y.getAverageRate() - x.getAverageRate())));

        for (int i = 0; i < foods.size(); i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).getAverageRate() < foods.get(j).getAverageRate()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    public void sortFoodLowRating() {
        for (int i = 0; i < foods.size(); i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).getAverageRate() > foods.get(j).getAverageRate()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    public void sortFoodHighPrice() {
        for (int i = 0; i < foods.size(); i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).getPrice() < foods.get(j).getPrice()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    public void sortFoodLowPrice() {
        for (int i = 0; i < foods.size(); i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).getPrice() > foods.get(j).getPrice()) {
                    Collections.swap(foods, i, j);
                }
            }
        }
    }

    @Override
    public String toString() {
        return name + "\t" + getAverageRate() + "\t";
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
