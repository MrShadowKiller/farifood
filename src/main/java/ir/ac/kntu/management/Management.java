package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.enums.adminmenu.StartMenuOptions;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class Management {
    private Database database;

    private InputObjectHandler inputObjectHandler;

    private CustomerService customerService ;

    private AdminService adminService ;

    public Management(Database database,InputObjectHandler inputObjectHandler) {
        this.database = database;
        customerService = new CustomerService(database,this);
        adminService = new AdminService(database,this);
        this.inputObjectHandler = inputObjectHandler;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void startMenu() {
        printStartMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (StartMenuOptions.findOption(userInput)) {
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
        for (Admin admin : database.getAdmins()) {
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
        for (Customer customer : database.getCustomers()) {
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
