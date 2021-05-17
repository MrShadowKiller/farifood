package ir.ac.kntu;

import ir.ac.kntu.restaurant.Restaurant;
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

    public void printDefaultRestaurants(ArrayList<Restaurant> restaurants, Customer customer){
        int count = 1;
        for (Restaurant restaurant : restaurants){
            if (restaurant.isOpen(customer.getUserSetting().getCurrentDay())){
                System.out.println("["+ count +"]. " + getBriefInfoOfRestaurant(restaurant));
                count++;
            }
        }
        System.out.println("[" + count + "]. " + "Exit");
    }


    public String getBriefInfoOfRestaurant(Restaurant restaurant){
        return restaurant.getName() + "\t" + restaurant.getAverageRate() + restaurant.getWorkHoursOpen() +
                "-"+restaurant.getWorkHoursClose();
    }


}
