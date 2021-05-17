package ir.ac.kntu;

import ir.ac.kntu.customermenu.BuyFoodTabOptions;
import ir.ac.kntu.customermenu.CustomerMenuOptions;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.SelectRestaurantManager;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;
import java.util.Collections;

public class CustomerService {
    private ArrayList<Restaurant> restaurants;

    private ArrayList<Food> foods;

    private ArrayList<Customer> customers;

    private ArrayList<Order> orders;

    private ViewCustomer viewCustomer;

    private InputObjectHandler inputObjectHandler;

    private SelectRestaurantManager selectRestaurantManager;

    public CustomerService(Customer customer, ArrayList<Restaurant> restaurants,
                           ArrayList<Food> foods, ArrayList<Order> orders,
                           ViewCustomer viewCustomer, InputObjectHandler inputObjectHandler) {
        customers = new ArrayList<>();
        customers.add(customer);
        this.restaurants = restaurants;
        this.foods = foods;
        this.orders = orders;
        this.viewCustomer = viewCustomer;
        this.inputObjectHandler = inputObjectHandler;
        selectRestaurantManager = new SelectRestaurantManager();
    }


    public void customerMenuHandler(Customer customer){
        setUserSetting(customer);
        viewCustomer.printCustomerMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomerMenuOptions userChoice = CustomerMenuOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);

        switch (userChoice) {
            case RESTAURANTS_FOODS:
                break;
            case EDIT_INFORMATION:
                break;
            case SHOW_INFORMATION:
                break;
            case ADD_BALANCE:
                break;
            case SETTING:
                break;
            case EXIT:
                return;
            default:
                customerMenuHandler(customer);
        }
    }

    public void setUserSetting(Customer customer) {
        System.out.println("To provide better support we need to customize the app with your preferences.");
        customer.setUserSetting(inputObjectHandler.scanUserSetting(viewCustomer));
    }

    public void restaurantsFoodsTabHandler(Customer customer){
        viewCustomer.printRestaurantFoodTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        BuyFoodTabOptions userChoice = BuyFoodTabOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice){
            case SHOW_RESTAURANTS:
                showDefaultRestaurants(customer);
                break;
            case SHOW_BEST_THREE:
                showBestThreeRestaurants(customer);
                break;
            case SHOW_WITH_BEST_FOOD:
                showBestRestaurantsForFood(customer);
                break;
            case SEARCH_BY_NAME:
                searchByRestaurantName(customer);
                break;
            case SEARCH_BY_TYPE:
                break;
            case EXIT: customerMenuHandler(customer);
                break;
            default:
                restaurantsFoodsTabHandler(customer);
        }
    }

    public void showDefaultRestaurants(Customer customer){
        setRestaurantSort(customer);
        Restaurant selectedRestaurant = selectRestaurantManager.selectDefaultRestaurantCustomer(restaurants,customer,viewCustomer);
        if (selectedRestaurant == null){
            restaurantsFoodsTabHandler(customer);
        } else {
            restaurantMenu(selectedRestaurant,customer);
        }
    }

    public void showBestThreeRestaurants(Customer customer){
        sortRestaurantHighRating();
        if (restaurants.size() < 3){
            System.out.println("Not enough Restaurants!");
            restaurantsFoodsTabHandler(customer);
        }
        Restaurant selectedRestaurant = selectRestaurantManager.selectBestThreeRestaurant(restaurants,customer,viewCustomer);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (selectedRestaurant == null){
            restaurantsFoodsTabHandler(customer);
        } else {
            restaurantMenu(selectedRestaurant,customer);
        }
    }

    public void showBestRestaurantsForFood(Customer customer){
        sortRestaurantHighRating();
        Restaurant selectedRestaurant = selectRestaurantManager.selectBestRestaurantForFood(restaurants,foods,viewCustomer,customer);
        if (selectedRestaurant == null){
            restaurantsFoodsTabHandler(customer);
        } else {
            restaurantMenu(selectedRestaurant,customer);
        }
    }

    public void searchByRestaurantName(Customer customer){
        Restaurant selectedRestaurant = selectRestaurantManager.findRestaurantByName(restaurants,customer);
        if (selectedRestaurant == null){
            restaurantsFoodsTabHandler(customer);
        } else {
            restaurantMenu(selectedRestaurant,customer);
        }
    }

    public void searchByRestaurantType(Customer customer){
        Restaurant selectedRestaurant = selectRestaurantManager.selectRestaurantByType(restaurants,customer,viewCustomer);
        if (selectedRestaurant == null){
            restaurantsFoodsTabHandler(customer);
        } else {
            restaurantMenu(selectedRestaurant,customer);
        }
    }

    public void restaurantMenu(Restaurant restaurant,Customer customer){

    }

    public void setRestaurantSort(Customer customer){
        switch (customer.getUserSetting().getRestaurantSortOption()){
            case LOW_RATE:
                sortRestaurantLowRating();
                break;
            case HIGH_RATE:
                sortRestaurantHighRating();
            case HIGH_COMMENTS:
                sortRestaurantHighComments();
                break;
            case LOW_COMMENTS:
                sortRestaurantLowComments();
                break;
        }
    }

    public void sortRestaurantHighRating() {
        for (int i = 0; i < restaurants.size(); i++) {
            for (int j = i + 1; j < restaurants.size(); j++) {
                if (restaurants.get(i).getAverageRate() < restaurants.get(j).getAverageRate()) {
                    Collections.swap(restaurants, i, j);
                }
            }
        }
    }

    public void sortRestaurantLowRating() {
        for (int i = 0; i < restaurants.size(); i++) {
            for (int j = i + 1; j < restaurants.size(); j++) {
                if (restaurants.get(i).getAverageRate() > restaurants.get(j).getAverageRate()) {
                    Collections.swap(restaurants, i, j);
                }
            }
        }
    }

    public void sortRestaurantHighComments() {
        for (int i = 0; i < restaurants.size(); i++) {
            for (int j = i + 1; j < restaurants.size(); j++) {
                if (restaurants.get(i).getComments().size() < restaurants.get(j).getComments().size()) {
                    Collections.swap(restaurants, i, j);
                }
            }
        }
    }

    public void sortRestaurantLowComments() {
        for (int i = 0; i < restaurants.size(); i++) {
            for (int j = i + 1; j < restaurants.size(); j++) {
                if (restaurants.get(i).getComments().size() > restaurants.get(j).getComments().size()) {
                    Collections.swap(restaurants, i, j);
                }
            }
        }
    }

}
