package ir.ac.kntu.management;

import ir.ac.kntu.objects.Food;
import ir.ac.kntu.enums.adminmenu.StartMenuOptions;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class Management {
    private CustomerService customerService;

    private AdminService adminService;

    private ArrayList<Admin> admins;

    private ArrayList<Restaurant> restaurants;

    private ArrayList<Food> foods;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Customer> customers;

    private ArrayList<Order> orders;

    private InputObjectHandler inputObjectHandler;

    public Management(Admin admin) {
        admins = new ArrayList<>();
        admins.add(admin);
        restaurants = new ArrayList<>();
        foods = new ArrayList<>();
        deliveries = new ArrayList<>();
        customers = new ArrayList<>();
        customers.add(admin);
        orders = new ArrayList<>();
        customerService = new CustomerService(restaurants, foods, orders, this);
        adminService = new AdminService(admin, admins, restaurants, foods, deliveries, customers, orders, this);
        inputObjectHandler = new InputObjectHandler();
    }

    public Management(Admin admin, ArrayList<Admin> admins, ArrayList<Restaurant> restaurants, ArrayList<Delivery> deliveries,
                      ArrayList<Customer> customers, ArrayList<Food> foods) {
        this.admins = admins;
        this.restaurants = restaurants;
        this.foods = new ArrayList<>();
        this.deliveries = deliveries;
        this.customers = customers;
        customers.add(admins.get(0));
        orders = new ArrayList<>();

        customerService = new CustomerService(restaurants, foods, orders, this);
        adminService = new AdminService(admin, admins, restaurants, foods, deliveries, customers, orders, this);
        inputObjectHandler = new InputObjectHandler();
    }


    public InputObjectHandler getInputObjectHandler() {
        return inputObjectHandler;
    }

    public void setInputObjectHandler(InputObjectHandler inputObjectHandler) {
        this.inputObjectHandler = inputObjectHandler;
    }

    public void startMenu() {
        printStartMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        StartMenuOptions userChoice = StartMenuOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);

        switch (userChoice) {
            case ADMIN_LOGIN:
                adminLoginVerify();
                break;
            case CUSTOMER_LOGIN:
                customerLoginVerify();
                break;
            case EXIT:
                return;
            default:
                startMenu();
        }
        startMenu();
    }

    public void adminLoginVerify() {
        String[] adminLoginDetails = inputObjectHandler.scanCustomerLogin();
        boolean foundAdmin = false;
        for (Admin admin : admins) {
            if (admin.getUsername().equals(adminLoginDetails[0]) &&
                    admin.getPassword().equals(adminLoginDetails[1])) {
                foundAdmin = true;
                System.out.println("Welcome!");
                adminService.adminMenuHandler(admin);
                break;
            }
        }
        if (!foundAdmin) {
            System.out.println("Wrong username or password!");
        }
    }

    public void customerLoginVerify() {
        String[] customerLoginDetails = inputObjectHandler.scanCustomerLogin();
        boolean foundCustomer = false;
        for (Customer customer : customers) {
            if (customer.getUsername().equals(customerLoginDetails[0]) &&
                    customer.getPassword().equals(customerLoginDetails[1])) {
                foundCustomer = true;
                System.out.println("Welcome!");
                customerService.customerMenuHandler(customer);
                break;
            }
        }
        if (!foundCustomer) {
            System.out.println("Wrong username or password!");
        }
        startMenu();
    }

    public void printStartMenu() {
        System.out.println(" ------- Welcome to Farifood Food Service ------- ");
        System.out.println("[1].Admin login");
        System.out.println("[2].Customer login");
        System.out.println("[3].Exit");
    }


}
