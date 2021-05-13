package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Restaurant {
    private String name;

    private String address;

    private String workHours;

    private RestaurantSchedule[] schedule;

    private RestaurantType restaurantType;

    private ArrayList<Food> foods;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Comment> comments;

    public Restaurant(String name, String address, RestaurantType restaurantType,
                      ArrayList<Food> foods) {
        this.name = name;
        this.address = address;
        schedule = RestaurantSchedule.values();
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

    public void addDelivery (Delivery delivery){
        deliveries.add(delivery);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }


    public double getAverageRate(){
        double averageRate =0;
        for (Comment comment : comments){
            averageRate += comment.getAverageRate();
        }
        return averageRate/ comments.size();
    }

    public void setDayAvailable(RestaurantSchedule day){
        for (RestaurantSchedule d : schedule){
            if (d == day){
                d.setAvailability(true);
            }
        }
    }

    public boolean isOpen(RestaurantSchedule day){
        for (RestaurantSchedule d : schedule){
            if (d == day && d.getAvailability()){
                return true;
            }
        }
        return false;
    }

    public void sortFoodHighRating(){
        for (int i=0;i<= foods.size();i++){
            for (int j=i+1;j <= foods.size();j++){
                if (foods.get(i).getAverageRate() < foods.get(j).getAverageRate()){
                    Collections.swap(foods,i,j);
                }
            }
        }
    }

    public void sortFoodLowRating(){
        for (int i=0;i<= foods.size();i++){
            for (int j=i+1;j <= foods.size();j++){
                if (foods.get(i).getAverageRate() > foods.get(j).getAverageRate()){
                    Collections.swap(foods,i,j);
                }
            }
        }
    }

    public void sortFoodHighPrice(){
        for (int i=0;i<= foods.size();i++){
            for (int j=i+1;j <= foods.size();j++){
                if (foods.get(i).getPrice() < foods.get(j).getPrice()){
                    Collections.swap(foods,i,j);
                }
            }
        }
    }

    public void sortFoodLowPrice(){
        for (int i=0;i<= foods.size();i++){
            for (int j=i+1;j <= foods.size();j++){
                if (foods.get(i).getPrice() > foods.get(j).getPrice()){
                    Collections.swap(foods,i,j);
                }
            }
        }
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
