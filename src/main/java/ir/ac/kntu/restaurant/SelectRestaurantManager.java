package ir.ac.kntu.restaurant;

import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.ViewCustomer;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class SelectRestaurantManager {
    public Restaurant selectDefaultRestaurantCustomer(ArrayList<Restaurant> restaurants, Customer customer, ViewCustomer viewCustomer){
        ArrayList<Restaurant> openRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.isOpen(customer.getUserSetting())){
                openRestaurants.add(restaurant);
            }
        }
        viewCustomer.printRestaurants(openRestaurants);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == openRestaurants.size()+1) {
            return null;
        } else {
            return openRestaurants.get(userChoice-1);
        }
    }


    public Restaurant selectBestThreeRestaurant(ArrayList<Restaurant> restaurants,Customer customer,ViewCustomer viewCustomer){
        ArrayList<Restaurant> bestThreeRestaurants = new ArrayList<>(3);
        int count = 1;
        for (Restaurant restaurant : restaurants){
            if (restaurant.isOpen(customer.getUserSetting())) {
                bestThreeRestaurants.add(restaurant);
                count ++;
            }
            if (count == 4){
                break;
            }
        }
        viewCustomer.printRestaurants(bestThreeRestaurants);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == bestThreeRestaurants.size()+1) {
            return null;
        } else {
            return bestThreeRestaurants.get(userChoice-1);
        }
    }

}
