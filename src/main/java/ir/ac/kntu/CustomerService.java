package ir.ac.kntu;

import ir.ac.kntu.adminmenu.StartMenuOptions;
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

    private View view;

    private InputObjectHandler inputObjectHandler;

    public CustomerService(Customer customer,ArrayList<Restaurant> restaurants,
                           ArrayList<Food> foods, ArrayList<Order> orders,
                           View view, InputObjectHandler inputObjectHandler) {
        customers = new ArrayList<>();
        customers.add(customer);
        this.restaurants = restaurants;
        this.foods = foods;
        this.orders = orders;
        this.view = view;
        this.inputObjectHandler = inputObjectHandler;
    }


    public void CustomerMenuHandler(Customer customer){
        setUserSetting(customer);
        view.printCustomerMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomerMenuOptions userChoice = CustomerMenuOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);

        switch (userChoice) {
            case BUY_FOOD:
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
                CustomerMenuHandler(customer);
        }
    }

    public void setUserSetting(Customer customer) {
        System.out.println("To provide better support we need to customize the app with your preferences.");
        customer.setUserSetting(inputObjectHandler.scanUserSetting(view));
    }

    public void BuyFoodHandler(Customer customer){

    }
}
