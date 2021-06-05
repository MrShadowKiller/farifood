package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.enums.customermenu.*;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.*;
import ir.ac.kntu.restaurant.sort.*;
import ir.ac.kntu.ui.ViewCustomer;
import ir.ac.kntu.user.Customer;
import java.time.LocalDateTime;

public class CustomerService {
    private final ViewCustomer viewCustomer;

    private final InputObjectHandler inputObjectHandler;

    private final SelectRestaurantManager selectRestaurantManager;

    private Database database;

    public CustomerService(Database database) {
        this.database = database;
        viewCustomer = new ViewCustomer();
        inputObjectHandler = new InputObjectHandler();
        inputObjectHandler.setDatabase(database);
        selectRestaurantManager = new SelectRestaurantManager();
    }

    public void customerMenuHandler(Customer customer) {
        if (!customer.getUserSetting().isAlreadyCreated()) {
            setUserSetting(customer);
        }
        viewCustomer.printCustomerMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (CustomerMenuOptions.findOption(userInput)) {
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
                return;
            default:
                customerMenuHandler(customer);
        }
        customerMenuHandler(customer);
    }

    public void editCustomerInformation(Customer customer) {
        inputObjectHandler.changeCustomerInformation(customer);
    }

    public void showCustomerInformationHandler(Customer customer) {
        viewCustomer.printShowCustomerTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (ShowCustomerOptions.findOption(userInput)) {
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
                return;
            default:
                showCustomerInformationHandler(customer);
                break;
        }
        showCustomerInformationHandler(customer);
    }

    public void showCustomerInfo(Customer customer) {
        System.out.println(customer.toString());
    }

    public void showCustomerOrders(Customer customer) {
        viewCustomer.printOrders(customer.getOrders());
    }

    public void showCustomerComments(Customer customer) {
        viewCustomer.printComments(customer.getComments());
    }

    public void addBalanceHandler(Customer customer) {
        viewCustomer.printAddBalanceTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AddBalanceOptions.findOption(userInput)) {
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
                return;
            default:
                addBalanceHandler(customer);
        }
        addBalanceHandler(customer);
    }

    public void addCreditCardHandler(Customer customer) {
        customer.setCreditCard(inputObjectHandler.scanCreditCard());
    }

    public void addWalletBalanceHandler(Customer customer) {
        System.out.println("How Much ?");
        double userInput = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput > customer.getCreditCard().getBalance()) {
            System.out.println("NOT ENOUGH BALANCE!");
        } else {
            customer.getCreditCard().useBalance(userInput);
            System.out.println("DONE!");
        }
    }

    public void addCreditCardBalanceHandler(Customer customer) {
        System.out.println("How Much ?");
        double userInput = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        customer.getCreditCard().addBalance(userInput);
    }

    public void setUserSetting(Customer customer) {
        System.out.println("To provide better support we need to customize the app with your preferences.");
        customer.setUserSetting(inputObjectHandler.scanUserSetting(viewCustomer));
    }

    public void restaurantsFoodsTabHandler(Customer customer) {
        viewCustomer.printRestaurantFoodTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (BuyFoodTabOptions.findOption(userInput)) {
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
            case SEARCH_BY_NEIGHBOR:
                searchByNeighbor(customer);
                break;
            case SHOW_FOOD_COMMENTS:
                showFoodCommentsHandler();
                break;
            case EXIT:
                return;
            default:
                restaurantsFoodsTabHandler(customer);
        }
        restaurantsFoodsTabHandler(customer);
    }

    public void showFoodCommentsHandler() {
        System.out.println("Which Food ?");
        viewCustomer.printFoodsWithPrice(database.getFoods());
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        for (Order order : database.getOrders()) {
            if (order.getFood().equals(database.getFoods().get(userInput - 1))) {
                System.out.println(order.getComment());
            }
        }
    }

    public void showDefaultRestaurants(Customer customer) {
        setRestaurantSort(customer);
        Restaurant selectedRestaurant = selectRestaurantManager.selectDefaultRestaurantCustomer(database.getRestaurants(), customer, viewCustomer);
        if (selectedRestaurant != null) {
            restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void showBestThreeRestaurants(Customer customer) {
        sortRestaurantHighRating();
        if (database.getRestaurants().size() < 3) {
            System.out.println("Not enough Restaurants!");
            restaurantsFoodsTabHandler(customer);
        }
        Restaurant selectedRestaurant = selectRestaurantManager.selectBestThreeRestaurant(database.getRestaurants(), customer, viewCustomer);
        if (selectedRestaurant != null) {
            restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void showBestRestaurantsForFood(Customer customer) {
        sortRestaurantHighRating();
        Restaurant selectedRestaurant = selectRestaurantManager.selectBestRestaurantForFood(database.getRestaurants(), database.getFoods(), viewCustomer, customer);
        if (selectedRestaurant != null) {
            restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void searchByRestaurantName(Customer customer) {
        Restaurant selectedRestaurant = selectRestaurantManager.findRestaurantByName(database.getRestaurants(), customer);
        if (selectedRestaurant != null) {
            restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void searchByRestaurantType(Customer customer) {
        Restaurant selectedRestaurant = selectRestaurantManager.selectRestaurantByType(database.getRestaurants(), customer, viewCustomer);
        if (selectedRestaurant != null) {
            restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void searchByNeighbor(Customer customer) {
        Restaurant selectedRestaurant = selectRestaurantManager.selectNearRestaurant(database.getRestaurants(), customer, viewCustomer);
        if (selectedRestaurant != null) {
            restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void restaurantMenu(Restaurant restaurant, Customer customer) {
        setFoodRestaurantSort(restaurant, customer);
        viewCustomer.printRestaurantMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (RestaurantMenuOptions.findOption(userInput)) {
            case SHOW_INFORMATION:
                showRestaurantInformationHandler(restaurant);
                break;
            case BUY_FOOD:
                buyFoodHandler(restaurant, customer);
                break;
            case SHOW_COMMENTS:
                viewCustomer.printComments(restaurant.getComments());
                break;
            case EXIT:
                return;
            default:
                restaurantMenu(restaurant, customer);
        }
        restaurantMenu(restaurant, customer);
    }

    public void showRestaurantInformationHandler(Restaurant restaurant) {
        viewCustomer.printRestaurantInformation(restaurant);
    }

    public void buyFoodHandler(Restaurant restaurant, Customer customer) {
        viewCustomer.printRestaurantFoodMenu(restaurant);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput != restaurant.getFoods().size() + 1) {
            processTheOrderCost(restaurant.getFoods().get(userInput - 1), customer, restaurant);
        }
    }

    public void processTheOrderCost(Food food, Customer customer, Restaurant restaurant) {
        System.out.println("Buy With : ");
        System.out.println("[1].Wallet");
        System.out.println("[2].Credit Card");
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput == 1) {
            checkCustomerWallet(food, customer, restaurant);
        } else if (userInput == 2) {
            checkCustomerCreditCard(food, customer, restaurant);
        }
    }

    public void checkCustomerWallet(Food food, Customer customer, Restaurant restaurant) {
        if (customer.getWallet().getBalance() < food.getPrice()) {
            System.out.println("Not Enough Money!");
            buyFoodHandler(restaurant, customer);
        } else {
            customer.getWallet().useBalance(food.getPrice());
            makeNewCustomerOrder(food, customer, restaurant);
        }
    }

    public void checkCustomerCreditCard(Food food, Customer customer, Restaurant restaurant) {
        if (customer.getCreditCard().getBalance() < food.getPrice()) {
            System.out.println("Not Enough Money!");
            buyFoodHandler(restaurant, customer);
        } else {
            customer.getCreditCard().useBalance(food.getPrice());
            makeNewCustomerOrder(food, customer, restaurant);
        }
    }

    public void makeNewCustomerOrder(Food food, Customer customer, Restaurant restaurant) {
        Delivery delivery = restaurant.getFreeDelivery(customer.getUserSetting().getCurrentDay());
        if (delivery == null) {
            System.out.println("There is no Delivery available");
            customer.getWallet().addBalance(food.getPrice());
        } else {
            Comment comment = inputObjectHandler.scanCommentFields(viewCustomer, customer, food, restaurant, delivery);
            Order order = new Order(customer, restaurant, food, delivery, LocalDateTime.now());
            restaurant.addOrder(order);
            restaurant.addComment(comment);
            customer.addOrder(order);
            customer.addComment(comment);
            delivery.addOrder(order);
            database.getOrders().add(order);
            order.setComment(comment);
        }
    }


    public void setRestaurantSort(Customer customer) {

        switch (customer.getUserSetting().getRestaurantSortOption()) {
            case LOW_RATE:
                sortRestaurantLowRating();
                break;
            case HIGH_RATE:
                sortRestaurantHighRating();
                break;
            case HIGH_COMMENTS:
                sortRestaurantHighComments();
                break;
            case LOW_COMMENTS:
                sortRestaurantLowComments();
                break;
            case RISING:
                sortRestaurantByRising();
                break;
            default:
                setRestaurantSort(customer);
        }
    }

    public void sortRestaurantHighRating() {
        database.getRestaurants().sort(new RestaurantCompareHighRate());
    }

    public void sortRestaurantLowRating() {
        database.getRestaurants().sort(new RestaurantCompareLowRate());
    }

    public void sortRestaurantHighComments() {
        database.getRestaurants().sort(new RestaurantCompareHighComments());
    }

    public void sortRestaurantLowComments() {
        database.getRestaurants().sort(new RestaurantCompareLowComments());
    }

    public void sortRestaurantByRising() {
        database.getRestaurants().sort(new RestaurantCompareRising());
    }

    public void setFoodRestaurantSort(Restaurant restaurant, Customer customer) {
        switch (customer.getUserSetting().getFoodSortOption()) {
            case LOW_PRICE:
                restaurant.sortFoodLowPrice();
                break;
            case HIGH_PRICE:
                restaurant.sortFoodHighPrice();
            case HIGH_RATE:
                restaurant.sortFoodHighRating();
                break;
            case LOW_RATE:
                restaurant.sortFoodLowRating();
            default:
                setFoodRestaurantSort(restaurant,customer);
        }
    }

}
