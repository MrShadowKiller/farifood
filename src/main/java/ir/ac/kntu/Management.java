package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;

import ir.ac.kntu.menu.*;
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
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        StartMenuOptions userChoice = StartMenuOptions.DEFAULT;

        StartMenuOptions[] options = StartMenuOptions.values();
        for (StartMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                userChoice = option;
            }
        }
        switch (userChoice) {
            case ADMIN_LOGIN:
                adminLoginVerify(admin);
                break;
            case CUSTOMER_LOGIN:
                System.out.println("NOT YET AVAILABLE!");
                startMenuHandler(admin);
                break;
            case EXIT:
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
        int adminOptionInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());

        AdminMenuOptions adminOptionChoice = AdminMenuOptions.DEFAULT;

        AdminMenuOptions[] options = AdminMenuOptions.values();
        for (AdminMenuOptions option : options) {
            if (option.getRate() == adminOptionInput - 1) {
                adminOptionChoice = option;
            }
        }

        switch (adminOptionChoice) {
            case ADMINS:
                adminsTabHandler(admin);
                break;
            case CUSTOMERS:
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
        view.printAdminsTab();
        int adminTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        AdminsTabOptions adminTabChoice = AdminsTabOptions.DEFAULT;

        AdminsTabOptions[] options = AdminsTabOptions.values();
        for (AdminsTabOptions option : options) {
            if (option.getRate() == adminTabInput - 1) {
                adminTabChoice = option;
            }
        }
        switch (adminTabChoice) {
            case ADD_ADMIN:
                addAdminHandler();
                break;
            case REMOVE_ADMIN:
                removeAdminHandler();
                break;
            case VIEW_EDIT_ADMIN:
                viewAndEditAdmins(admin);
                break;
            case EXIT:
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

    public void viewAndEditAdmins(Admin admin) {
        view.printAdmins(admins);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == admins.size() + 1){
            adminsTabHandler(admin);
        }
        editAdminHandler(admins.get(userChoice - 1));
    }

    public void editAdminHandler(Admin admin) {
        view.printAdminEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        AdminEditOptions userChoice = AdminEditOptions.DEFAULT;

        AdminEditOptions[] options = AdminEditOptions.values();
        for (AdminEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                userChoice = option;
            }
        }

        switch (userChoice) {
            case CHANGE_PERSONAL_INFO:
                admin.changeInformation();
                break;
            case CHANGE_PASSWORD:
                admin.changePassword();
                break;
            case CHANGE_BALANCE:
                admin.changeBalance();
                break;
            case EXIT:
                adminsTabHandler(admin);
                break;
            default:
                editAdminHandler(admin);
        }
        editAdminHandler(admin);
    }

    public void customersTabHandler(Admin admin) {
        view.printCustomersTab();
        int customerTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomersTabOptions customerTabChoice = CustomersTabOptions.DEFAULT;

        CustomersTabOptions[] options = CustomersTabOptions.values();
        for (CustomersTabOptions option : options) {
            if (option.getRate() == customerTabInput - 1) {
                customerTabChoice = option;
            }
        }
        switch (customerTabChoice) {
            case ADD_CUSTOMER:
                addCustomerHandler();
                break;
            case REMOVE_CUSTOMER:
                removeCustomerHandler();
                break;
            case VIEW_EDIT_CUSTOMER:
                viewCustomers(admin);
                break;
            case VIEW_CUSTOMER_ORDERS:
                viewCustomerOrders(admin);
                break;
            case EXIT:
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

    public void viewCustomers(Admin admin) {
        view.printCustomers(customers);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == customers.size() + 1){
            customersTabHandler(admin);
        }
        editCustomerHandler(customers.get(userChoice - 1), admin);
    }

    public void editCustomerHandler(Customer customer, Admin admin) {
        view.printCustomerEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomerEditOptions userChoice = CustomerEditOptions.DEFAULT;

        CustomerEditOptions[] options = CustomerEditOptions.values();
        for (CustomerEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                userChoice = option;
            }
        }

        switch (userChoice) {
            case CHANGE_PERSONAL_INFO:
                customer.changeInformation();
                break;
            case CHANGE_PASSWORD:
                customer.changePassword();
                break;
            case CHANGE_BALANCE:
                customer.changeBalance();
                break;
            case EXIT:
                customersTabHandler(admin);
                break;
            default:
                editCustomerHandler(customer, admin);
        }
        editCustomerHandler(customer, admin);
    }

    public void viewCustomerOrders(Admin admin) {
        System.out.println("Which Customer ?");
        viewCustomers(admin);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        view.printCustomerOrders(customers.get(userChoice - 1));
    }

    public void restaurantsTabHandler(Admin admin) {
        view.printRestaurantsTab();
    }

}
