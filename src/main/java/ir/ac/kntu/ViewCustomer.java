package ir.ac.kntu;

import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class ViewCustomer {

    public void printCustomerMenu(){
        System.out.println("\tPlease use of the below options");
        System.out.println("[1].Restaurants and Foods");
        System.out.println("[2].Edit Information");
        System.out.println("[3].Show Information");
        System.out.println("[4].Add Balance");
        System.out.println("[5].Setting");
        System.out.println("[6].Exit");
    }

    public void printRestaurantFoodTab(){
        System.out.println("[1].Show Restaurants (By User Setting)");
        System.out.println("[2].Show Best 3 Restaurants and their Foods");
        System.out.println("[3].Show Restaurants with the best Food");
        System.out.println("[4].Search Restaurant By Name");
        System.out.println("[5].Search Restaurant By Type");
        System.out.println("[6].Exit");
    }

    public void printRestaurants(ArrayList<Restaurant> restaurants){
        for (int i=1;i<=restaurants.size();i++){
            System.out.println("["+ i +"]. " + getBriefInfoOfRestaurant(restaurants.get(i-1)));
        }
        System.out.println("[" + (restaurants.size()+1) + "]. " + "Exit");
    }

    public String getBriefInfoOfRestaurant(Restaurant restaurant){
        return restaurant.getName() + "\t" + restaurant.getAverageRate() + " " + restaurant.getWorkHoursOpen() +
                "-"+restaurant.getWorkHoursClose();
    }

    public void printBestThreeRestaurants(ArrayList<Restaurant> restaurants){
        for (int i=1;i<=restaurants.size();i++){
            System.out.println("["+ i +"]. " + getBriefInfoOfRestaurant(restaurants.get(i-1)));
            printBestThreeFoods(restaurants.get(i-1));
        }
        System.out.println("[" + (restaurants.size()+1) + "]. " + "Exit");
    }


    public void printBestThreeFoods(Restaurant restaurant){
        restaurant.sortFoodHighRating();
        for (int i = 1; i <= 3; i++) {
            System.out.println("(" + i + "). " + restaurant.getFoods().get(i-1));
        }
    }
    public void printRestaurantSortOptions(RestaurantSortOption[] restaurantOptions){
        for (int i = 1; i <= restaurantOptions.length; i++) {
            System.out.println("[" + i + "]. " + restaurantOptions[i-1].getName());
        }
    }

    public void printFoodSortOptions(FoodSortOption[] foodOptions){
        for (int i = 1; i <= foodOptions.length; i++) {
            System.out.println("[" + i + "]. " + foodOptions[i-1].getName());
        }
    }

    public void printWeekDays(){
        WeekDays[] weekDays = WeekDays.values();
        for (int i = 1; i <= weekDays.length; i++) {
            System.out.println("[" + i + "]. " + weekDays[i-1].toString());
        }
    }

    public void printFoodsWithoutPrice(ArrayList<Food> foods){
        for (int i=1;i<=foods.size();i++){
            System.out.println("["+ i +"]. " + foods.get(i-1).getName() +
                    "  " + foods.get(i-1).getTimeForCooking() + "min ");
        }
        if (foods.size()==0){
            System.out.println("EMPTY!");
            return;
        }
    }

    public void printRestaurantTypes(){
        RestaurantType[] restaurantType = RestaurantType.values();
        for (int i = 1; i <= restaurantType.length; i++) {
            System.out.println("[" + i + "]. " + restaurantType[i-1].toString());
        }
    }



}
