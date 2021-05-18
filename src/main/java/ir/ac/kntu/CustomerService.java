package ir.ac.kntu;

import ir.ac.kntu.customermenu.*;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.SelectRestaurantManager;
import ir.ac.kntu.user.Customer;

import java.time.LocalDateTime;
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

    private Management management;

    public CustomerService(ArrayList<Restaurant> restaurants, ArrayList<Food> foods,
                           ArrayList<Order> orders ,Management management) {
        customers = new ArrayList<>();
        this.restaurants = restaurants;
        this.foods = foods;
        this.orders = orders;
        viewCustomer = new ViewCustomer();
        inputObjectHandler = new InputObjectHandler();
        selectRestaurantManager = new SelectRestaurantManager();
        this.management = management;
    }


    public void customerMenuHandler(Customer customer){
        if (!customer.getUserSetting().isAlreadyCreated()) {
            setUserSetting(customer);
        }
        viewCustomer.printCustomerMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomerMenuOptions userChoice = CustomerMenuOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);

        switch (userChoice) {
            case RESTAURANTS_FOODS:
                restaurantsFoodsTabHandler(customer);
                break;
            case EDIT_INFORMATION:
                editCustomerInformation(customer);
                break;
            case SHOW_INFORMATION:
                showCustomerInformationHandler(customer);
                break;
            case ADD_BALANCE:
                addBalanceHandler(customer);
                break;
            case SETTING:
                setUserSetting(customer);
                break;
            case EXIT:
                management.startMenu();
            default:
                customerMenuHandler(customer);
        }
        customerMenuHandler(customer);
    }

    public void editCustomerInformation(Customer customer){
        inputObjectHandler.changeCustomerInformation(customer);
    }

    public void showCustomerInformationHandler(Customer customer){
        viewCustomer.printShowCustomerTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        ShowCustomerOptions userChoice = ShowCustomerOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);

        switch(userChoice){
            case SHOW_INFO:
               showCustomerInfo(customer);
                break;
            case SHOW_ORDERS:
                showCustomerOrders(customer);
                break;
            case SHOW_COMMENTS:
                showCustomerComments(customer);
                break;
            case EXIT:
                customerMenuHandler(customer);
                break;
            default :
                showCustomerInformationHandler(customer);
                break;
        }
        showCustomerInformationHandler(customer);
    }

    public void showCustomerInfo(Customer customer){
        System.out.println(customer.toString());
    }

    public void showCustomerOrders(Customer customer){
        viewCustomer.printOrders(customer.getOrders());
    }

    public void showCustomerComments(Customer customer){
        viewCustomer.printComments(customer.getComments());
    }

    public void addBalanceHandler(Customer customer){
        viewCustomer.printAddBalanceTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        AddBalanceOptions userChoice = AddBalanceOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);

        switch(userChoice){
            case ADD_CREDIT_CARD:
                addCreditCardHandler(customer);
                break;
            case ADD_WALLET_BALANCE:
                addWalletBalanceHandler(customer);
                break;
            case ADD_CREDIT_CARD_BALANCE:
                addCreditCardBalanceHandler(customer);
                break;
            case EXIT:
                customerMenuHandler(customer);
                break;
            default:
                addBalanceHandler(customer);
        }
        addBalanceHandler(customer);
    }

    public void addCreditCardHandler(Customer customer){
        customer.setCreditCard(inputObjectHandler.scanCreditCard());
    }

    public void addWalletBalanceHandler(Customer customer){
        System.out.println("How Much ?");
        double userInput = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput > customer.getCreditCard().getBalance()){
            System.out.println("NOT ENOUGH BALANCE!");
        } else {
            customer.getCreditCard().useBalance(userInput);
            System.out.println("DONE!");
        }
    }

    public void addCreditCardBalanceHandler(Customer customer){
        System.out.println("How Much ?");
        double userInput = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        customer.getCreditCard().addBalance(userInput);
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
                searchByRestaurantType(customer);
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
        viewCustomer.printRestaurantMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantMenuOptions userChoice = RestaurantMenuOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice){
            case SHOW_INFORMATION:
                showRestaurantInformationHandler(restaurant);
                break;
            case BUY_FOOD:
                buyFoodHandler(restaurant,customer);
                break;
            case SHOW_COMMENTS:
                showRestaurantComments(restaurant);
                break;
            case EXIT:
                restaurantsFoodsTabHandler(customer);
                break;
            default:
                restaurantMenu(restaurant,customer);
        }
        restaurantMenu(restaurant,customer);
    }

    public void showRestaurantInformationHandler(Restaurant restaurant){
        viewCustomer.printRestaurantInformation(restaurant);
    }

    public void showRestaurantComments(Restaurant restaurant){
        viewCustomer.printComments(restaurant.getComments());
    }

    public void buyFoodHandler(Restaurant restaurant,Customer customer){
        viewCustomer.printRestaurantFoodMenu(restaurant);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput == restaurant.getFoods().size() + 1){
            restaurantMenu(restaurant,customer);
        } else {
            processTheOrderCost(restaurant.getFoods().get(userInput-1),customer,restaurant);
        }
    }

    public void processTheOrderCost(Food food,Customer customer,Restaurant restaurant){
        System.out.println("Buy With : ");
        System.out.println("[1].Wallet");
        System.out.println("[2].Credit Card");
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput == 1){
            checkCustomerWallet(food,customer,restaurant);
        } else if (userInput == 2){
            checkCustomerCreditCard(food,customer,restaurant);
        }
    }

    public void checkCustomerWallet(Food food,Customer customer,Restaurant restaurant){
        if (customer.getWallet().getBalance() < food.getPrice()){
            System.out.println("Not Enough Money!");
            buyFoodHandler(restaurant,customer);
        } else {
            customer.getWallet().useBalance(food.getPrice());
            makeNewCustomerOrder(food,customer,restaurant);
        }
    }

    public void checkCustomerCreditCard(Food food,Customer customer,Restaurant restaurant){
        if (customer.getCreditCard().getBalance() < food.getPrice()){
            System.out.println("Not Enough Money!");
            buyFoodHandler(restaurant,customer);
        } else {
            customer.getCreditCard().useBalance(food.getPrice());
            makeNewCustomerOrder(food,customer,restaurant);
        }
    }

    public void makeNewCustomerOrder(Food food,Customer customer,Restaurant restaurant){
        Delivery delivery = restaurant.getFreeDelivery(customer.getUserSetting().getCurrentDay());
        if (delivery == null){
            System.out.println("There is no Delivery available");
            customer.getWallet().addBalance(food.getPrice());
        } else {
            Comment comment = inputObjectHandler.scanCommentFields(viewCustomer,customer,food,restaurant,delivery);
            Order order = new Order(customer,restaurant,food,delivery, LocalDateTime.now());
            restaurant.addOrder(order);
            restaurant.addComment(comment);
            customer.addOrder(order);
            customer.addComment(comment);
            orders.add(order);
        }
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
