package ir.ac.kntu.logic;

import ir.ac.kntu.Database;
import ir.ac.kntu.logic.services.AdminService;
import ir.ac.kntu.logic.services.CustomerService;
import ir.ac.kntu.logic.services.SellerManService;
import ir.ac.kntu.models.enums.adminmenu.StartMenuOptions;
import ir.ac.kntu.models.user.Admin;
import ir.ac.kntu.models.user.Customer;
import ir.ac.kntu.models.user.SellerMan;
import ir.ac.kntu.util.InputObjectHandler;
import ir.ac.kntu.util.ScannerWrapper;

public class Management {
    private Database database;

    private final CustomerService customerService;

    private final AdminService adminService;

    private final SellerManService sellerManService;

    public Management(Database database) {
        this.database = database;
        customerService = new CustomerService(database);
        adminService = new AdminService(database);
        sellerManService = new SellerManService(database);

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
            case SELLERMAN_LOGIN:
                sellerManLoginVerify();
                break;
            case EXIT:
                return;
            default:
                startMenu();
        }
        startMenu();
    }

    public void adminLoginVerify() {
        String[] adminLoginDetails = InputObjectHandler.getInstance().scanUserLogin();
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
        String[] customerLoginDetails = InputObjectHandler.getInstance().scanUserLogin();
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

    public void sellerManLoginVerify() {
        String[] sellerManLoginDetails = InputObjectHandler.getInstance().scanUserLogin();
        boolean foundSellerMan = false;
        for (SellerMan sellerMan : database.getSellerMen()) {
            if (sellerMan.getUsername().equals(sellerManLoginDetails[0]) &&
                    sellerMan.getPassword().equals(sellerManLoginDetails[1])) {
                foundSellerMan = true;
                System.out.println("Welcome!");
                sellerManService.sellerManMenuStart(sellerMan);
                break;
            }
        }
        if (!foundSellerMan) {
            System.out.println("Wrong username or password!");
        }
    }

    public void printStartMenu() {
        System.out.println(" ------- Welcome to Farifood Food Service ------- ");
        System.out.println("[1].Admin login");
        System.out.println("[2].Customer login");
        System.out.println("[3].SellerMan login");
        System.out.println("[4].Exit");

    }


}
