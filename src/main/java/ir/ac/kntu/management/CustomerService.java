package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.Department;
import ir.ac.kntu.Stackable;
import ir.ac.kntu.enums.customermenu.*;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.StackableItem;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.*;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerService {
    private final ViewPerson viewPerson;

    private final Database database;

    private final CustomerServiceMenuHandler customerServiceMenuHandler;

    public CustomerService(Database database) {
        this.database = database;
        viewPerson = new ViewPerson();
        customerServiceMenuHandler = new CustomerServiceMenuHandler(this, database, viewPerson);
    }

    public void customerMenuStart(Customer customer) {
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
            if (order.getItems().contains(database.getFoods().get(userInput - 1))) {
                System.out.println(order.getComment());
            }
        }
    }

    public void showDefaultRestaurants(Customer customer) {
        setRestaurantSort(customer);
        Restaurant selectedRestaurant = Selector.getInstance().selectDefaultRestaurantCustomer(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.departmentMenu(selectedRestaurant, customer);
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
            customerServiceMenuHandler.departmentMenu(selectedRestaurant, customer);
        }
    }

    public void showBestRestaurantsForFood(Customer customer) {
        database.sortRestaurantHighRating();
        Restaurant selectedRestaurant = Selector.getInstance().selectBestRestaurantForFood(database.getRestaurants(), database.getFoods(), viewPerson, customer);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.departmentMenu(selectedRestaurant, customer);
        }
    }

    public void searchByRestaurantName(Customer customer) {
        Restaurant selectedRestaurant = Selector.getInstance().findRestaurantByName(database.getRestaurants(), customer);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.departmentMenu(selectedRestaurant, customer);
        }
    }

    public void searchByRestaurantType(Customer customer) {
        Restaurant selectedRestaurant = Selector.getInstance().selectRestaurantByType(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.departmentMenu(selectedRestaurant, customer);
        }
    }

    public void searchByNeighbor(Customer customer) {
        Restaurant selectedRestaurant = Selector.getInstance().selectNearRestaurant(database.getRestaurants(), customer, viewPerson);
        if (selectedRestaurant != null) {
            customerServiceMenuHandler.departmentMenu(selectedRestaurant, customer);
        }
    }

    public void showDepartmentInformationHandler(Department department) {
        viewPerson.printDepartmentInformation(department);
    }

    public void buyItemHandler(Department department, Customer customer) {
        department.getDepartmentItemMenu(viewPerson);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput != department.getItems().size() + 1) {
            if (department.getItems().get(userInput - 1) instanceof StackableItem) {
                if (((StackableItem) department.getItems().get(userInput - 1)).isOutOfStock()) {
                    System.out.println("OUT OF STOCK!");
                    return;
                }
            }
            customer.addItemToBasket(department.getItems().get(userInput - 1));
        }

    }

    public void checkOutItemsHandler(Customer customer, Department department) {
        if (customer.getBasket().isEmpty()){
            System.out.println("Basket is empty!");
            return;
        }
        processTheOrderCost(customer, department);
    }

    public void processTheOrderCost(Customer customer, Department department) {
        System.out.println("Buy With : ");
        System.out.println("[1].Wallet");
        System.out.println("[2].Credit Card");
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userInput == 1) {
            checkCustomerWallet(customer, department);
        } else if (userInput == 2) {
            checkCustomerCreditCard(customer, department);
        }
    }

    public void checkCustomerWallet(Customer customer, Department department) {
        if (customer.getWallet().getBalance() < customer.getBasketCost()) {
            System.out.println("Not Enough Money!");
            customerServiceMenuHandler.customerMenuTabHandler(customer);
        } else {
            customer.getWallet().useBalance(customer.getBasketCost());
            makeNewCustomerOrder(customer, department);
        }
    }

    public void checkCustomerCreditCard(Customer customer, Department department) {
        if (customer.getCreditCard().getBalance() < customer.getBasketCost()) {
            System.out.println("Not Enough Money!");
            customerServiceMenuHandler.customerMenuTabHandler(customer);
        } else {
            customer.getCreditCard().useBalance(customer.getBasketCost());
            makeNewCustomerOrder(customer, department);
        }
    }

    public void makeNewCustomerOrder(Customer customer, Department department) {
        Order order = department.checkOutCustomerOrder(customer);
        if (order == null) {
            customer.getWallet().addBalance(customer.getBasketCost());
        } else {
            Comment comment = InputObjectHandler.getInstance().scanCommentFields(viewPerson, customer, customer.getBasket(), department, order.getDelivery());
            department.addOrder(order);
            department.addComment(comment);
            customer.addOrder(order);
            customer.addComment(comment);
            database.getOrders().add(order);
            order.setComment(comment);
        }
        customer.emptyBasket();
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

    public void setItemDepartmentSort(Department department, Customer customer) {
        switch (customer.getUserSetting().getFoodSortOption()) {
            case LOW_PRICE:
                department.sortItemLowPrice();
                break;
            case HIGH_PRICE:
                department.sortItemHighPrice();
            case HIGH_RATE:
                department.sortItemHighRating();
                break;
            case LOW_RATE:
                department.sortItemLowRating();
                break;
            default:
                setItemDepartmentSort(department, customer);
        }
    }

}
