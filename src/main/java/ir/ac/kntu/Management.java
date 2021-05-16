package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;

import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
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
//        setUserSetting(admin);
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
            case DELIVERIES:
                deliveriesTabHandler(admin);
                break;
            case FOOD: foodTabHandler(admin);
                break;
            case RESTAURANTS: restaurantsTabHandler(admin);
                break;
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
                break;
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
        if (userChoice == admins.size() + 1) {
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
                changeCustomerInfo(admin);
                break;
            case CHANGE_PASSWORD:
                changeCustomerPassword(admin);
                break;
            case CHANGE_BALANCE:
                changeCustomerBalance(admin);
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
                viewAndEditCustomers(admin);
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

    public void viewAndEditCustomers(Admin admin) {
        view.printCustomers(customers);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == customers.size() + 1) {
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
                changeCustomerInfo(customer);
                break;
            case CHANGE_PASSWORD:
                changeCustomerPassword(customer);
                break;
            case CHANGE_BALANCE:
                changeCustomerBalance(customer);
                break;
            case EXIT:
                customersTabHandler(admin);
                break;
            default:
                editCustomerHandler(customer, admin);
        }
        editCustomerHandler(customer, admin);
    }

    public void changeCustomerInfo(Customer customer){
        inputObjectHandler.changeCustomerInformation(customer);
    }

    public void changeCustomerPassword(Customer customer){
        System.out.print("New Password : ");
        String newPassword = ScannerWrapper.getInstance().nextLine().trim();
        if (customer.checkPasswordValidation(newPassword)){
            customer.setPassword(newPassword);
        } else {
            System.out.println("Invalid Password!");
        }
    }

    public void changeCustomerBalance(Customer customer){
        System.out.print("New Balance : ");
        customer.getWallet().setBalance(Double.parseDouble(ScannerWrapper.getInstance().nextLine()));
    }

    public void viewCustomerOrders(Admin admin) {
        System.out.println("Which Customer ?");
        viewAndEditCustomers(admin);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        view.printOrders(customers.get(userChoice - 1).getOrders());
    }

    public void restaurantsTabHandler(Admin admin) {
        view.printRestaurantsTab();
    }

    public void deliveriesTabHandler(Admin admin) {
        view.printDeliveriesTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        DeliveriesTabOptions userChoice = DeliveriesTabOptions.DEFAULT;

        DeliveriesTabOptions[] options = DeliveriesTabOptions.values();
        for (DeliveriesTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                userChoice = option;
            }
        }

        switch (userChoice) {
            case ADD_DELIVERY:
                addDeliveryHandler();
                break;
            case REMOVE_DELIVERY:
                removeDeliveryHandler();
                break;
            case VIEW_EDIT_DELIVERIES:
                viewAndEditDeliveries(admin);
                break;
            case VIEW_ORDERS:
                 viewDeliveryOrders(admin);
            case EXIT:
                adminMenuHandler(admin);
                break;
            default:
                deliveriesTabHandler(admin);
        }
        deliveriesTabHandler(admin);
    }

    public void addDeliveryHandler() {
        deliveries.add(inputObjectHandler.scanDeliveryInfo(view));
    }

    public void removeDeliveryHandler() {
        System.out.println("Choose one of the deliveries : ");
        view.printDeliveries(deliveries);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        deliveries.remove(userInput - 1);
        System.out.println("Done!");
    }

    public void viewDeliveryOrders(Admin admin) {
        System.out.println("Which Delivery ?");
        view.printDeliveries(deliveries);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == deliveries.size() + 1) {
            deliveriesTabHandler(admin);
        }
        view.printOrders(deliveries.get(userChoice - 1).getOrders());
    }

    public void viewAndEditDeliveries(Admin admin) {
        view.printDeliveries(deliveries);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == deliveries.size() + 1) {
            deliveriesTabHandler(admin);
        }
        editDeliveryHandler(deliveries.get(userChoice - 1), admin);
    }

    public void editDeliveryHandler(Delivery delivery, Admin admin) {
        view.printDeliveryEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        DeliveryEditOptions userChoice = DeliveryEditOptions.DEFAULT;

        DeliveryEditOptions[] options = DeliveryEditOptions.values();
        for (DeliveryEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                userChoice = option;
            }
        }

        switch (userChoice) {
            case CHANGE_SALARY:
                changeDeliverySalary(delivery);
                break;
            case CHANGE_VEHICLE:
                changeDeliveryVehicle(delivery);
                break;
            case CHANGE_SALARY_TYPE:
                changeDeliverySalaryType(delivery);
                break;
            case EXIT:
                deliveriesTabHandler(admin);
                break;
            default:
                editDeliveryHandler(delivery, admin);
        }
        editDeliveryHandler(delivery, admin);
    }

    public void changeDeliverySalary(Delivery delivery) {
        System.out.println("How Much ? ");
        delivery.setSalary(Double.parseDouble(ScannerWrapper.getInstance().nextLine()));
    }

    //NEED_CHANGE
//    public void changeDeliveryRestaurants(Delivery delivery) {
//        if (restaurants.size()==0){
//            System.out.println("THERE IS NO RESTAURANT!");
//            return;
//        }
//        System.out.println("Which restaurant to add ?");
//        view.printRestaurants(restaurants);
//        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
//        System.out.println("Which index of restaurant ?");
//        view.printDeliveryRestaurants(delivery);
//        int userInput2 = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
//        delivery.getRestaurants()[userInput2 - 1] = restaurants.get(userInput - 1);
//    }

    public void changeDeliveryVehicle(Delivery delivery) {
        DeliveryVehicle[] deliveryVehicles = DeliveryVehicle.values();
        System.out.println("Which vehicle ?");
        view.printDeliveryVehicles();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        delivery.setVehicleType(deliveryVehicles[userInput - 1]);
    }

    public void changeDeliverySalaryType(Delivery delivery){
        SalaryType[] salaryTypes = SalaryType.values();
        System.out.println("Which Salary ?");
        view.printSalaryTypes();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        delivery.setSalaryType(salaryTypes[userInput - 1]);
    }

    public void foodTabHandler(Admin admin){
        view.printFoodTab();
        int foodTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        FoodTabOptions foodTabChoice = FoodTabOptions.DEFAULT;

        FoodTabOptions[] options = FoodTabOptions.values();
        for (FoodTabOptions option : options) {
            if (option.getRate() == foodTabInput - 1) {
                foodTabChoice = option;
            }
        }
        switch (foodTabChoice) {
            case ADD_FOOD:
                addFoodHandler();
                break;
            case REMOVE_FOOD:
                removeFoodHandler();
                break;
            case VIEW_FOODS:
                viewFoodsHandler();
                break;
            case VIEW_FOOD_COMMENTS:
                viewFoodCommentsHandler();
                break;
            case EXIT:
                adminMenuHandler(admin);
                break;
            default:
                foodTabHandler(admin);
        }
        foodTabHandler(admin);
    }

    public void addFoodHandler(){
        foods.add(inputObjectHandler.scanFoodInfo());
    }

    public void removeFoodHandler(){
        foods.remove(inputObjectHandler.scanFoodInfo());
    }

    public void viewFoodsHandler(){
        view.printFoods(foods);
    }

    public void viewFoodCommentsHandler(){
        view.printFoodComments(inputObjectHandler.selectFood(view,foods),restaurants);
    }

}
