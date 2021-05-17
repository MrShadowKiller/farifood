package ir.ac.kntu.restaurant;

import ir.ac.kntu.Food;
import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.ViewCustomer;
import ir.ac.kntu.user.Customer;

import java.lang.invoke.SwitchPoint;
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
        for (Restaurant restaurant : restaurants){
            if (restaurant.isOpen(customer.getUserSetting())) {
                bestThreeRestaurants.add(restaurant);
            }
            if (bestThreeRestaurants.size() == 3){
                break;
            }
        }
        viewCustomer.printBestThreeRestaurants(bestThreeRestaurants);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == bestThreeRestaurants.size()+1) {
            return null;
        } else {
            return bestThreeRestaurants.get(userChoice-1);
        }
    }

    public Restaurant selectBestRestaurantForFood(ArrayList<Restaurant> restaurants,ArrayList<Food> foods,
                                                  ViewCustomer viewCustomer,Customer customer){
        System.out.println("For which food ?");
        viewCustomer.printFoodsWithoutPrice(foods);
        System.out.println("[" + (foods.size()+1) + "]. " + "Exit");
        int foodChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (foodChoice == foods.size()+1) {
            return null;
        }
        ArrayList<Restaurant> selectedRestaurant = findRestaurantWithFood(restaurants,foods.get(foodChoice-1),customer);
        System.out.println("Choose one :");
        viewCustomer.printRestaurants(selectedRestaurant);
        int restaurantChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (restaurantChoice == selectedRestaurant.size() + 1){
            return null;
        }
        return selectedRestaurant.get(restaurantChoice-1);
    }

    public ArrayList<Restaurant> findRestaurantWithFood(ArrayList<Restaurant> restaurants,Food food,Customer customer){
        ArrayList<Restaurant> result = new ArrayList<>();
        for (int i=1;i<=restaurants.size();i++){
            if (restaurants.get(i-1).getFoods().contains(food) && restaurants.get(i-1).isOpen(customer.getUserSetting())){
                result.add(restaurants.get(i-1));
            }
            if (result.size() == 5){
                break;
            }
        }

        return result;
    }

    public Restaurant findRestaurantByName(ArrayList<Restaurant> restaurants,Customer customer){
        System.out.print("Restaurant Name: ");
        String restaurantName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Restaurant Neighbor");
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        for (Restaurant restaurant : restaurants ){
            if (restaurant.getName().equals(restaurantName) &&
                    restaurant.getAddress().getNeighbor().equals(neighbor)){
                if (restaurant.isOpen(customer.getUserSetting())) {
                    return restaurant;
                }
            }
        }
        System.out.println("Didnt Found!");
        return null;
    }

    public Restaurant selectRestaurantByType(ArrayList<Restaurant> restaurants,Customer customer,ViewCustomer viewCustomer){
        RestaurantType restaurantType = selectRestaurantType(viewCustomer);
        ArrayList<Restaurant> sameRestaurantTypes = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.getRestaurantType() == restaurantType && restaurant.isOpen(customer.getUserSetting())){
                sameRestaurantTypes.add(restaurant);
            }
        }
        if (sameRestaurantTypes.size() == 0 ){
            System.out.println("Not Found!");
            return null;
        }
        System.out.println("Select One :");
        viewCustomer.printRestaurants(sameRestaurantTypes);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return restaurants.get(userChoice-1);
    }

    public RestaurantType selectRestaurantType(ViewCustomer viewCustomer){
        System.out.println("Which restaurant Type ?");
        viewCustomer.printRestaurantTypes();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantType restaurantType = RestaurantType.DEFAULT;

        return (restaurantType.findOption(userChoice-1));
    }

}
