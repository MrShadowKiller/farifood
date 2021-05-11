package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Restaurant {
    private String name;

    private String address;

    private String workHours;

    private WeekDays[] weeklySchedules;

    private RestaurantType restaurantType;

    private ArrayList<Food> foods;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Comment> comments;

    public Restaurant(String name, String address, RestaurantType restaurantType,
                      ArrayList<Food> foods) {
        this.name = name;
        this.address = address;
        weeklySchedules = WeekDays.values();
        this.restaurantType = restaurantType;
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public WeekDays[] getWeeklySchedules() {
        return weeklySchedules;
    }

    public void setWeeklySchedules(WeekDays[] weeklySchedules) {
        this.weeklySchedules = weeklySchedules;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
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

    public void addFood(Food food){
        foods.add(food);
    }


    public double getAverageRate(){
        double averageRate =0;
        for (Comment comment : comments){
            averageRate += comment.getAverageRate();
        }
        return averageRate/ comments.size();
    }

    public void setDayAvailable(WeekDays day){
        for (WeekDays d : weeklySchedules){
            if (d == day){
                d.setAvailability(true);
            }
        }
    }

    public boolean isOpen(WeekDays day){
        for (WeekDays d : weeklySchedules){
            if (d == day && d.getAvailability()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
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
