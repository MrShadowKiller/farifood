package ir.ac.kntu.management;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.adminmenu.*;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderStatus;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import java.util.ArrayList;
public class AdminService {
    private final ArrayList<Admin> admins;

    private final ArrayList<Restaurant> restaurants;

    private final ArrayList<Food> foods;

    private final ArrayList<Delivery> deliveries;

    private final ArrayList<Customer> customers;

    private final ArrayList<Order> orders;

    private final ViewAdmin viewAdmin;

    private final InputObjectHandler inputObjectHandler;

    private final Management management;

    public AdminService(Admin admin, ArrayList<Admin> admins, ArrayList<Restaurant> restaurants, ArrayList<Food> foods,
                        ArrayList<Delivery> deliveries, ArrayList<Customer> customers, ArrayList<Order> orders, Management management) {
        this.admins = admins;
        admins.add(admin);
        this.restaurants = restaurants;
        this.foods = foods;
        this.deliveries = deliveries;
        this.customers = customers;
        this.orders = orders;
        viewAdmin = new ViewAdmin();
        inputObjectHandler = new InputObjectHandler();
        this.management = management;
    }

    public void adminMenuHandler(Admin admin) {
        viewAdmin.printAdminStartMenu();
        int adminOptionInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        AdminMenuOptions adminOptionChoice = AdminMenuOptions.DEFAULT;
        adminOptionChoice = adminOptionChoice.findOption(adminOptionInput);
        switch (adminOptionChoice) {
            case ADMINS: adminsTabHandler(admin);
                break;
            case CUSTOMERS: customersTabHandler(admin);
                break;
            case DELIVERIES: deliveriesTabHandler(admin);
                break;
            case FOOD: foodTabHandler(admin);
                break;
            case RESTAURANTS: restaurantsTabHandler(admin);
                break;
            case ORDERS: ordersTabHandler(admin);
                break;
            case EXIT: return;
            default: adminMenuHandler(admin);
        }
        adminMenuHandler(admin);
    }

    public void ordersTabHandler(Admin admin) {
        OrderStatus[] orderStatuses = OrderStatus.values();
        System.out.println("Which Status ?");
        viewAdmin.printOrderStatus();
        int orderStatusChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        viewAdmin.printOrdersByStatus(orders, orderStatuses[orderStatusChoice - 1]);
        System.out.println("[" + (orders.size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == orders.size() + 1) {
            return;
        } else {
            changeOrderStatus(orders.get(userChoice - 1));
        }
    }

    public void changeOrderStatus(Order order) {
        OrderStatus[] orderStatuses = OrderStatus.values();
        System.out.println("Change status to :");
        viewAdmin.printOrderStatus();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        order.setOrderStatus(orderStatuses[userChoice - 1]);
    }

    public void adminsTabHandler(Admin admin) {
        viewAdmin.printAdminsTab();
        int adminTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        AdminsTabOptions adminTabChoice = AdminsTabOptions.DEFAULT;
        adminTabChoice = adminTabChoice.findOption(adminTabInput);
        switch (adminTabChoice) {
            case ADD_ADMIN: addAdminHandler();
                break;
            case REMOVE_ADMIN: removeAdminHandler();
                break;
            case VIEW_EDIT_ADMIN: viewAndEditAdmins(admin);
                break;
            case EXIT: return;
            default: adminsTabHandler(admin);
        }
        adminsTabHandler(admin);
    }

    public void addAdminHandler() {
        Admin newAdmin = inputObjectHandler.scanAdminInfo();
        admins.add(newAdmin);
        customers.add(newAdmin);
    }

    public void removeAdminHandler() {
        inputObjectHandler.findAndRemoveAdmin(admins,customers);
    }

    public void viewAndEditAdmins(Admin admin) {
        viewAdmin.printAdmins(admins);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == admins.size() + 1) {
            return;
        } else {
            editAdminHandler(admins.get(userChoice - 1));
        }
    }

    public void editAdminHandler(Admin admin) {
        viewAdmin.printAdminEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        AdminEditOptions userChoice = AdminEditOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice) {
            case CHANGE_PERSONAL_INFO: changeCustomerInfo(admin);
                break;
            case CHANGE_PASSWORD: changeCustomerPassword(admin);
                break;
            case CHANGE_BALANCE: changeCustomerBalance(admin);
                break;
            case EXIT: return;
            default: editAdminHandler(admin);
        }
        editAdminHandler(admin);
    }

    public void customersTabHandler(Admin admin) {
        viewAdmin.printCustomersTab();
        int customerTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomersTabOptions customerTabChoice = CustomersTabOptions.DEFAULT;
        customerTabChoice = customerTabChoice.findOption(customerTabInput);
        switch (customerTabChoice) {
            case ADD_CUSTOMER: addCustomerHandler();
                break;
            case REMOVE_CUSTOMER: removeCustomerHandler();
                break;
            case VIEW_EDIT_CUSTOMER: viewAndEditCustomers(admin);
                break;
            case VIEW_CUSTOMER_ORDERS: viewCustomerOrders(admin);
                break;
            case VIEW_COMMENTS: viewCustomerComments(admin);
                break;
            case EXIT: return;
            default: customersTabHandler(admin);
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
        editCustomerHandler(selectCustomerHandler(admin), admin);
    }

    public void viewCustomerComments(Admin admin) {
        viewAdmin.printComments(selectCustomerHandler(admin).getComments());
    }

    public Customer selectCustomerHandler(Admin admin) {
        viewAdmin.printCustomers(customers);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == customers.size() + 1) {
            customersTabHandler(admin);
        } else {
            return customers.get(userChoice - 1);
        }
        return null;
    }

    public void editCustomerHandler(Customer customer, Admin admin) {
        viewAdmin.printCustomerEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        CustomerEditOptions userChoice = CustomerEditOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice) {
            case CHANGE_PERSONAL_INFO: changeCustomerInfo(customer);
                break;
            case CHANGE_PASSWORD: changeCustomerPassword(customer);
                break;
            case CHANGE_BALANCE: changeCustomerBalance(customer);
                break;
            case EXIT: return;
            default: editCustomerHandler(customer, admin);
        }
        editCustomerHandler(customer, admin);
    }

    public void changeCustomerInfo(Customer customer) {
        inputObjectHandler.changeCustomerInformation(customer);
    }

    public void changeCustomerPassword(Customer customer) {
        System.out.print("New Password : ");
        String newPassword = ScannerWrapper.getInstance().nextLine().trim();
        if (customer.checkPasswordValidation(newPassword)) {
            customer.setPassword(newPassword);
        } else {
            System.out.println("Invalid Password!");
        }
    }

    public void changeCustomerBalance(Customer customer) {
        System.out.print("New Balance : ");
        customer.getWallet().setBalance(Double.parseDouble(ScannerWrapper.getInstance().nextLine()));
    }

    public void viewCustomerOrders(Admin admin) {
        System.out.println("Which Customer ?");
        viewAndEditCustomers(admin);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        viewAdmin.printOrders(customers.get(userChoice - 1).getOrders());
    }

    public void restaurantsTabHandler(Admin admin) {
        viewAdmin.printRestaurantsTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantsTabOptions userChoice = RestaurantsTabOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice) {
            case ADD_RESTAURANT: addRestaurantHandler();
                break;
            case REMOVE_RESTAURANT: removeRestaurantHandler();
                break;
            case VIEW_EDIT_RESTAURANT: viewAndEditRestaurantHandler(admin);
                break;
            case VIEW_ORDERS: viewRestaurantOrders(admin);
                break;
            case VIEW_FOODS: viewRestaurantFoods(admin);
                break;
            case VIEW_COMMENTS: viewRestaurantComments(admin);
            case VIEW_DELIVERIES: viewRestaurantDeliveries(admin);
                break;
            case EXIT: return;
            default: restaurantsTabHandler(admin);
        }
        restaurantsTabHandler(admin);
    }

    public void addRestaurantHandler() {
        restaurants.add(inputObjectHandler.scanRestaurantInfo(viewAdmin, foods));
    }

    public void removeRestaurantHandler() {
        restaurants.remove(inputObjectHandler.findRestaurant(restaurants));
    }

    public void viewRestaurantOrders(Admin admin) {
        viewAdmin.printOrders(selectRestaurantHandler(admin).getOrders());
    }

    public void viewRestaurantFoods(Admin admin) {
        viewAdmin.printFoods(selectRestaurantHandler(admin).getFoods());
    }

    public void viewRestaurantComments(Admin admin) {
        viewAdmin.printComments(selectRestaurantHandler(admin).getComments());
    }

    public void viewRestaurantDeliveries(Admin admin) {
        viewAdmin.printDeliveries(selectRestaurantHandler(admin).getDeliveries());
    }

    public void viewAndEditRestaurantHandler(Admin admin) {
        Restaurant selectedRestaurant = selectRestaurantHandler(admin);
        viewAdmin.printEditRestaurantTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantEditOptions userChoice = RestaurantEditOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice) {
            case CHANGE_NAME: changeRestaurantName(selectedRestaurant);
                break;
            case CHANGE_WORK_HOURS: changeRestaurantWorkHours(selectedRestaurant);
                break;
            case CHANGE_SCHEDULE: changeRestaurantSchedule(selectedRestaurant);
                break;
            case ADD_FOOD: addFoodRestaurant(selectedRestaurant);
                break;
            case REMOVE_FOOD: removeFoodRestaurant(selectedRestaurant);
                break;
            case ADD_DELIVERY: addDeliveryRestaurant(selectedRestaurant);
                break;
            case REMOVE_DELIVERY: removeDeliveryRestaurant(selectedRestaurant);
                break;
            case EXIT: return;
            default: viewAndEditRestaurantHandler(admin);
        }
        viewAndEditRestaurantHandler(admin);
    }

    public void changeRestaurantName(Restaurant restaurant) {
        System.out.print("New Name : ");
        restaurant.setName(ScannerWrapper.getInstance().nextLine().trim());
    }

    public void changeRestaurantWorkHours(Restaurant restaurant) {
        inputObjectHandler.selectRestaurantWorkHours(restaurant);
    }

    public void changeRestaurantSchedule(Restaurant restaurant) {
        System.out.println("Which days restaurant is available ? ");
        restaurant.setSchedule(inputObjectHandler.selectRestaurantSchedule(viewAdmin));
    }

    public void addFoodRestaurant(Restaurant restaurant) {
        Food food = new Food(inputObjectHandler.selectFood(viewAdmin, foods));
        System.out.print("price : ");
        double userChoice = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        food.setPrice(userChoice);
        restaurant.addFood(food);
    }

    public void removeFoodRestaurant(Restaurant restaurant) {
        restaurant.getFoods().remove(inputObjectHandler.selectFood(viewAdmin, restaurant.getFoods()));
    }

    public void addDeliveryRestaurant(Restaurant restaurant) {
        restaurant.addDelivery(inputObjectHandler.selectRestaurantDelivery(viewAdmin, deliveries, restaurant));
    }

    public void removeDeliveryRestaurant(Restaurant restaurant) {
        Delivery delivery = inputObjectHandler.findRestaurantDelivery(viewAdmin, restaurant);
        delivery.removeRestaurant(restaurant);
        restaurant.getDeliveries().remove(delivery);
    }

    public Restaurant selectRestaurantHandler(Admin admin) {
        viewAdmin.printRestaurants(restaurants);
        System.out.println("[" + (restaurants.size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == restaurants.size() + 1) {
            restaurantsTabHandler(admin);
        } else {
            return restaurants.get(userChoice - 1);
        }
        return null;
    }

    public void deliveriesTabHandler(Admin admin) {
        viewAdmin.printDeliveriesTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        DeliveriesTabOptions userChoice = DeliveriesTabOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice) {
            case ADD_DELIVERY: addDeliveryHandler();
                break;
            case REMOVE_DELIVERY: removeDeliveryHandler();
                break;
            case VIEW_EDIT_DELIVERIES: viewAndEditDeliveries(admin);
                break;
            case VIEW_ORDERS: viewDeliveryOrders(admin);
            case EXIT: return;
            default: deliveriesTabHandler(admin);
        }
        deliveriesTabHandler(admin);
    }

    public void addDeliveryHandler() {
        deliveries.add(inputObjectHandler.scanDeliveryInfo(viewAdmin));
    }

    public void removeDeliveryHandler() {
        deliveries.remove(inputObjectHandler.selectToRemoveDelivery(deliveries,viewAdmin));
    }

    public void viewDeliveryOrders(Admin admin) {
        System.out.println("Which Delivery ?");
        viewAdmin.printDeliveries(deliveries);
        System.out.println("[" + (deliveries.size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == deliveries.size() + 1) {
            deliveriesTabHandler(admin);
        } else {
            viewAdmin.printOrders(deliveries.get(userChoice - 1).getOrders());
        }
    }

    public void viewAndEditDeliveries(Admin admin) {
        viewAdmin.printDeliveries(deliveries);
        System.out.println("[" + (deliveries.size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == deliveries.size() + 1) {
            return;
        } else {
            editDeliveryHandler(deliveries.get(userChoice - 1), admin);
        }
    }

    public void editDeliveryHandler(Delivery delivery, Admin admin) {
        viewAdmin.printDeliveryEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        DeliveryEditOptions userChoice = DeliveryEditOptions.DEFAULT;
        userChoice = userChoice.findOption(userInput);
        switch (userChoice) {
            case CHANGE_SALARY: changeDeliverySalary(delivery);
                break;
            case CHANGE_VEHICLE: changeDeliveryVehicle(delivery);
                break;
            case CHANGE_SALARY_TYPE: changeDeliverySalaryType(delivery);
                break;
            case EXIT: deliveriesTabHandler(admin);
                break;
            default: editDeliveryHandler(delivery, admin);
        }
        editDeliveryHandler(delivery, admin);
    }

    public void changeDeliverySalary(Delivery delivery) {
        System.out.println("How Much ? ");
        delivery.setSalary(Double.parseDouble(ScannerWrapper.getInstance().nextLine()));
    }

    public void changeDeliveryVehicle(Delivery delivery) {
        delivery.setVehicleType(inputObjectHandler.selectDeliveryVehicle());
    }

    public void changeDeliverySalaryType(Delivery delivery) {
        delivery.setSalaryType(inputObjectHandler.selectDeliverySalaryType());
    }

    public void foodTabHandler(Admin admin) {
        viewAdmin.printFoodTab();
        int foodTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        FoodTabOptions foodTabChoice = FoodTabOptions.DEFAULT;
        foodTabChoice = foodTabChoice.findOption(foodTabInput);
        switch (foodTabChoice) {
            case ADD_FOOD: addFoodHandler();
                break;
            case REMOVE_FOOD: removeFoodHandler();
                break;
            case VIEW_FOODS: viewFoodsHandler();
                break;
            case VIEW_FOOD_COMMENTS: viewFoodCommentsHandler();
                break;
            case EXIT: return;
            default: foodTabHandler(admin);
        }
        foodTabHandler(admin);
    }

    public void addFoodHandler() {
        foods.add(inputObjectHandler.scanFoodInfo());
    }

    public void removeFoodHandler() {
        foods.remove(inputObjectHandler.scanFoodInfo());
    }

    public void viewFoodsHandler() {
        viewAdmin.printFoods(foods);
    }

    public void viewFoodCommentsHandler() {
        viewAdmin.printFoodComments(inputObjectHandler.selectFood(viewAdmin, foods), restaurants);
    }
}
