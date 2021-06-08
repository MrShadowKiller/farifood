package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.enums.adminmenu.StartMenuOptions;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

public class Management {
    private Database database;

    private final CustomerService customerService;

    private final AdminService adminService;

    public Management(Database database) {
        this.database = database;
        customerService = new CustomerService(database);
        adminService = new AdminService(database);

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
        String[] adminLoginDetails = InputObjectHandler.getInstance().scanCustomerLogin();
        boolean foundAdmin = false;
        for (Admin admin : database.getAdmins()) {
            if (admin.getUsername().equals(adminLoginDetails[0]) &&
                    admin.getPassword().equals(adminLoginDetails[1])) {
                foundAdmin = true;
                System.out.println("Welcome!");
                adminService.adminMenuStart(admin);
                break;
            }
        }
        if (!foundAdmin) {
            System.out.println("Wrong username or password!");
        }
    }

    public void customerLoginVerify() {
        String[] customerLoginDetails = InputObjectHandler.getInstance().scanCustomerLogin();
        boolean foundCustomer = false;
        for (Customer customer : database.getCustomers()) {
            if (customer.getUsername().equals(customerLoginDetails[0]) &&
                    customer.getPassword().equals(customerLoginDetails[1])) {
                foundCustomer = true;
                System.out.println("Welcome!");
                customerService.customerMenuStart(customer);
                break;
            }
        }
        if (!foundCustomer) {
            System.out.println("Wrong username or password!");
        }
    }

    public void printStartMenu() {
        System.out.println(" ------- Welcome to Farifood Food Service ------- ");
        System.out.println("[1].Admin login");
        System.out.println("[2].Customer login");
        System.out.println("[3].Exit");
    }


}
