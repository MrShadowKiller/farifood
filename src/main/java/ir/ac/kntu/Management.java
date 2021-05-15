package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;

import ir.ac.kntu.restaurant.Restaurant;

import ir.ac.kntu.user.Admin;

import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class Management {
    private ArrayList<Admin> admins;

    private ArrayList<Restaurant> restaurants;

    private ArrayList<Food> foods;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Customer> customers;

    private View view;

    private InputObjectHandler inputObjectHandler;

    public Management(Admin admin) {
        admins = new ArrayList<>();
        admins.add(admin);
        restaurants = new ArrayList<>();
        foods = new ArrayList<>();
        deliveries = new ArrayList<>();
        customers = new ArrayList<>();
        customers.add(admin);
        view = new View();
        inputObjectHandler = new InputObjectHandler();
    }

    public void startMenuHandler(Admin admin) {
        view.printStartMenu();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());

        switch (userChoice) {
            case 1:
                adminLoginVerify(admin);
                break;
            case 2:
                System.out.println("NOT YET AVAILABLE!");
                startMenuHandler(admin);
                break;
            case 3:
                return;
            default:
                startMenuHandler(admin);
        }
    }

    public void adminLoginVerify(Admin admin) {
        String[] adminLoginDetails = inputObjectHandler.scanCustomerLogin();
        if (!admin.getUsername().equals(adminLoginDetails[0]) ||
                !admin.getPassword().equals(adminLoginDetails[1])) {
            System.out.println("Wrong username or password!");
            adminLoginVerify(admin);
        }

        setUserSetting(admin);

        adminMenuHandler(admin);
    }

    public void setUserSetting(Customer customer) {
        System.out.println("To provide better support we need to customize the app with your preferences.");
        customer.setUserSetting(inputObjectHandler.scanUserSetting(view));
    }

    public void adminMenuHandler(Admin admin) {
        view.printAdminStartMenu();
        int adminOptionChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (adminOptionChoice) {
            case 1:
                adminsTabHandler(admin);
                break;
            case 2:
                customersTabHandler(admin);
                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 5:
//                break;
//            case 7:
//                break;
//            case 8:
//                break;
            default:
                adminMenuHandler(admin);
        }

    }

    public void adminsTabHandler(Admin admin) {
        view.printAdminTab();
        int adminTabChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (adminTabChoice) {
            case 1:
                addAdminHandler();
                break;
            case 2:
                removeAdminHandler();
                break;
            case 3:
                viewAdmins();
                break;
            case 4:
                adminMenuHandler(admin);
                break;
            default:
                adminsTabHandler(admin);
        }
        adminsTabHandler(admin);
    }

    public void addAdminHandler() {
        Admin newAdmin = inputObjectHandler.scanAdminInfo();
        admins.add(newAdmin);
        customers.add(newAdmin);
    }

    public void removeAdminHandler() {
        String[] adminLoginDetails = inputObjectHandler.scanCustomerLogin();
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getUsername().equals(adminLoginDetails[0])
                    && admins.get(i).getPassword().equals(adminLoginDetails[1])) {
                admins.remove(i);
                System.out.println("Done!");
            }
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUsername().equals(adminLoginDetails[0])
                    && customers.get(i).getPassword().equals(adminLoginDetails[1])) {
                customers.remove(i);
                return;
            }
        }

        System.out.println("Cant find the admin!");

    }

    public void viewAdmins() {
        view.printAdmins(admins);
    }

    public void customersTabHandler(Admin admin) {
        view.printCustomersTab();
        int customerTabChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (customerTabChoice) {
            case 1:
                addCustomerHandler();
                break;
            case 2:
                removeCustomerHandler();
                break;
            case 3:
                viewCustomers();
                break;
            case 4:
                viewCustomerOrders();
                break;
            case 5:
                adminMenuHandler(admin);
            default:
                customersTabHandler(admin);
        }
        customersTabHandler(admin);
    }

    public void addCustomerHandler() {
        customers.add(inputObjectHandler.scanCustomerInfo());
    }

    public void removeCustomerHandler() {
        String[] customerLoginDetails = inputObjectHandler.scanCustomerLogin();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUsername().equals(customerLoginDetails[0])
                    && customers.get(i).getPassword().equals(customerLoginDetails[1])) {
                customers.remove(i);
                System.out.println("Done!");
                return;
            }
        }
        System.out.println("Cant find the Customer!");
    }

    public void viewCustomers() {
        view.printCustomers(customers);
    }

    public void viewCustomerOrders() {
        System.out.println("Which Customer ?");
        viewCustomers();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        view.printCustomerOrders(customers.get(userChoice - 1));
    }


}
