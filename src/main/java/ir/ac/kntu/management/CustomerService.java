package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.enums.customermenu.*;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.*;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.Customer;
import java.time.LocalDateTime;

public class CustomerService {
    private final ViewPerson viewPerson;

    private Database database;

    private final CustomerServiceMenuHandler customerServiceMenuHandler;

    public CustomerService(Database database) {
        this.database = database;
        viewPerson = new ViewPerson();
        customerServiceMenuHandler = new CustomerServiceMenuHandler(this,database,viewPerson);
    }

    public void customerMenuStart(Customer customer){
        customerServiceMenuHandler.customerMenuTabHandler(customer);
    }

    public void editCustomerInformation(Customer customer) {
        InputObjectHandler.getInstance().changeCustomerInformation(customer);
    }

    public void showCustomerInfo(Customer customer) {
        System.out.println(customer.toString());
    }

    public void showCustomerOrders(Customer customer) {
        viewPerson.printOrders(customer.getOrders());
    }

    public void showCustomerComments(Customer customer) {
        viewPerson.printComments(customer.getComments());
    }


    public void addCreditCardHandler(Customer customer) {
        customer.setCreditCard(InputObjectHandler.getInstance().scanCreditCard());
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
        customer.setUserSetting(InputObjectHandler.getInstance().scanUserSetting(viewPerson));
    }


    public void showFoodCommentsHandler() {
        System.out.println("Which Food ?");
        viewPerson.printFoodsWithPrice(database.getFoods());
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        for (Order order : database.getOrders()) {
            if (order.getItems().contains(database.getFoods().get(userInput - 1))){
                System.out.println(order.getComment());
            }
        }
    }

    public void showDefaultRestaurants(Customer customer) {
        setRestaurantSort(customer);
        Restaurant selectedRestaurant = Selector.getInstance().selectDefaultRestaurantCustomer(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void showBestThreeRestaurants(Customer customer) {
        database.sortRestaurantHighRating();
        if (database.getRestaurants().size() < 3) {
            System.out.println("Not enough Restaurants!");
            customerServiceMenuHandler.restaurantsFoodsTabHandler(customer);
        }
        Restaurant selectedRestaurant = Selector.getInstance().selectBestThreeRestaurant(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void showBestRestaurantsForFood(Customer customer) {
        database.sortRestaurantHighRating();
        Restaurant selectedRestaurant = Selector.getInstance().selectBestRestaurantForFood(database.getRestaurants(), database.getFoods(), viewPerson, customer);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void searchByRestaurantName(Customer customer) {
        Restaurant selectedRestaurant = Selector.getInstance().findRestaurantByName(database.getRestaurants(), customer);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void searchByRestaurantType(Customer customer) {
        Restaurant selectedRestaurant = Selector.getInstance().selectRestaurantByType(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void searchByNeighbor(Customer customer) {
        Restaurant selectedRestaurant = Selector.getInstance().selectNearRestaurant(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.restaurantMenu(selectedRestaurant, customer);
        }
    }

    public void showRestaurantInformationHandler(Restaurant restaurant) {
        viewPerson.printRestaurantInformation(restaurant);
    }

    public void buyFoodHandler(Restaurant restaurant, Customer customer) {
        viewPerson.printRestaurantFoodMenu(restaurant);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput != restaurant.getItems().size() + 1) {
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
            Comment comment = InputObjectHandler.getInstance().scanCommentFields(viewPerson, customer, food, restaurant, delivery);
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
                database.sortRestaurantLowRating();
                break;
            case HIGH_RATE:
                database.sortRestaurantHighRating();
                break;
            case HIGH_COMMENTS:
                database.sortRestaurantHighComments();
                break;
            case LOW_COMMENTS:
                database.sortRestaurantLowComments();
                break;
            case RISING:
                database.sortRestaurantByRising();
                break;
            default:
                setRestaurantSort(customer);
        }
    }

    public void setItemDepartmentSort(Restaurant restaurant, Customer customer) {
        switch (customer.getUserSetting().getFoodSortOption()) {
            case LOW_PRICE:
                restaurant.sortItemLowPrice();
                break;
            case HIGH_PRICE:
                restaurant.sortItemHighPrice();
            case HIGH_RATE:
                restaurant.sortItemHighRating();
                break;
            case LOW_RATE:
                restaurant.sortItemLowRating();
            default:
                setItemDepartmentSort(restaurant,customer);
        }
    }

}
