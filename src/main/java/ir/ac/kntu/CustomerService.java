package ir.ac.kntu;

import ir.ac.kntu.customermenu.BuyFoodTabOptions;
import ir.ac.kntu.customermenu.CustomerMenuOptions;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class CustomerService {
    private ArrayList<Restaurant> restaurants;

    private ArrayList<Food> foods;

    private ArrayList<Customer> customers;

    private ArrayList<Order> orders;

    private ViewCustomer viewCustomer;

    private InputObjectHandler inputObjectHandler;

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
                break;
            case SHOW_WITH_BEST_FOOD:
                break;
            case SEARCH_BY_NAME:
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
        Restaurant selectedRestaurant = inputObjectHandler.selectDefaultRestaurantCustomer(restaurants,customer,viewCustomer);
        if (selectedRestaurant == null){
            restaurantsFoodsTabHandler(customer);
        } else {
            restaurantMenu(selectedRestaurant,customer);
        }
    }


    public void showBestThreeRestaurants(){

    }








    public void restaurantMenu(Restaurant restaurant,Customer customer){

    }




}
