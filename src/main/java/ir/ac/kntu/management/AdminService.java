package ir.ac.kntu.management;
import ir.ac.kntu.Database;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.enums.adminmenu.*;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderStatus;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

public class AdminService {

    private final ViewAdmin viewAdmin;

    private final InputObjectHandler inputObjectHandler;

    private final Database database;

    public AdminService(Database database){
        this.database = database;
        viewAdmin = new ViewAdmin();
        inputObjectHandler = new InputObjectHandler();
        inputObjectHandler.setDatabase(database);
    }

    public void adminMenuHandler(Admin admin) {
        viewAdmin.printAdminStartMenu();
        int adminOptionInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AdminMenuOptions.findOption(adminOptionInput)) {
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
            case SUPERMARKET:

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
        viewAdmin.printOrdersByStatus(database.getOrders(), orderStatuses[orderStatusChoice - 1]);
        System.out.println("[" + (database.getOrders().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice != database.getOrders().size() + 1) {
            changeOrderStatus(database.getOrders().get(userChoice - 1));
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
        switch (AdminsTabOptions.findOption(adminTabInput)) {
            case ADD_ADMIN: database.addAdmin();
                break;
            case REMOVE_ADMIN: database.removeAdmin();
                break;
            case VIEW_EDIT_ADMIN: viewAndEditAdmins(admin);
                break;
            case EXIT: return;
            default: adminsTabHandler(admin);
        }
        adminsTabHandler(admin);
    }

    public void viewAndEditAdmins(Admin admin) {
        viewAdmin.printAdmins(database.getAdmins());
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice != database.getAdmins().size() + 1) {
            editAdminHandler(database.getAdmins().get(userChoice - 1));
        }
    }

    public void editAdminHandler(Admin admin) {
        viewAdmin.printAdminEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AdminEditOptions.findOption(userInput)) {
            case CHANGE_PERSONAL_INFO: inputObjectHandler.changeCustomerInformation(admin);
                break;
            case CHANGE_PASSWORD: database.changeUserPassword(admin);
                break;
            case CHANGE_BALANCE: database.changeCustomerBalance(admin);
                break;
            case EXIT: return;
            default: editAdminHandler(admin);
        }
        editAdminHandler(admin);
    }

    public void customersTabHandler(Admin admin) {
        viewAdmin.printCustomersTab();
        int customerTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (CustomersTabOptions.findOption(customerTabInput)) {
            case ADD_CUSTOMER: database.addCustomer();
                break;
            case REMOVE_CUSTOMER: database.removeCustomer();
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

    public void viewAndEditCustomers(Admin admin) {
        Customer selectedCustomer = inputObjectHandler.selectCustomerHandler(viewAdmin,admin);
        if (selectedCustomer != null){
            editCustomerHandler(selectedCustomer, admin);
        }
    }


    public void editCustomerHandler(Customer customer, Admin admin) {
        viewAdmin.printCustomerEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (CustomerEditOptions.findOption(userInput)) {
            case CHANGE_PERSONAL_INFO: inputObjectHandler.changeCustomerInformation(customer);
                break;
            case CHANGE_PASSWORD: database.changeUserPassword(customer);
                break;
            case CHANGE_BALANCE: database.changeCustomerBalance(customer);
                break;
            case EXIT: return;
            default: editCustomerHandler(customer, admin);
        }
        editCustomerHandler(customer, admin);
    }

    public void viewCustomerOrders(Admin admin) {
        System.out.println("Which Customer ?");
        viewAndEditCustomers(admin);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        viewAdmin.printOrders(database.getCustomers().get(userChoice - 1).getOrders());
    }

    public void viewCustomerComments(Admin admin) {
        viewAdmin.printComments(inputObjectHandler.selectCustomerHandler(viewAdmin,admin).getComments());
    }

    public void restaurantsTabHandler(Admin admin) {
        viewAdmin.printRestaurantsTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (RestaurantsTabOptions.findOption(userInput)) {
            case ADD_RESTAURANT: database.addRestaurant();
                break;
            case REMOVE_RESTAURANT: database.removeRestaurant();
                break;
            case VIEW_EDIT_RESTAURANT: viewAndEditRestaurantHandler(admin);
                break;
            case VIEW_ORDERS: viewRestaurantOrders(admin);
                break;
            case VIEW_FOODS: viewRestaurantFoods(admin);
                break;
            case VIEW_COMMENTS: viewRestaurantComments(admin);
                break;
            case VIEW_DELIVERIES: viewRestaurantDeliveries(admin);
                break;
            case EXIT: return;
            default: restaurantsTabHandler(admin);
        }
        restaurantsTabHandler(admin);
    }

    public void viewRestaurantOrders(Admin admin) {
        viewAdmin.printOrders(inputObjectHandler.selectRestaurantHandler(admin,viewAdmin).getOrders());
    }

    public void viewRestaurantFoods(Admin admin) {
        viewAdmin.printFoods(inputObjectHandler.selectRestaurantHandler(admin,viewAdmin).getFoods());
    }

    public void viewRestaurantComments(Admin admin) {
        viewAdmin.printComments(inputObjectHandler.selectRestaurantHandler(admin,viewAdmin).getComments());
    }

    public void viewRestaurantDeliveries(Admin admin) {
        viewAdmin.printDeliveries(inputObjectHandler.selectRestaurantHandler(admin,viewAdmin).getDeliveries());
    }

    public void viewAndEditRestaurantHandler(Admin admin) {
        Restaurant selectedRestaurant = inputObjectHandler.selectRestaurantHandler(admin,viewAdmin);
        if (selectedRestaurant == null){
            return;
        }
        viewAdmin.printEditRestaurantTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (RestaurantEditOptions.findOption(userInput)) {
            case CHANGE_NAME: database.changeRestaurantName(selectedRestaurant);
                break;
            case CHANGE_WORK_HOURS: database.changeRestaurantWorkHours(selectedRestaurant);
                break;
            case CHANGE_SCHEDULE: database.changeRestaurantSchedule(selectedRestaurant);
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

    public void addFoodRestaurant(Restaurant restaurant) {
        Food food = new Food(inputObjectHandler.selectFood(viewAdmin));
        System.out.print("price : ");
        double userChoice = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        food.setPrice(userChoice);
        restaurant.addItem(food);
    }

    public void removeFoodRestaurant(Restaurant restaurant) {
        restaurant.getItems().remove(inputObjectHandler.selectFood(viewAdmin));
    }

    public void addDeliveryRestaurant(Restaurant restaurant) {
        restaurant.addDelivery(inputObjectHandler.selectRestaurantDelivery(viewAdmin, restaurant));
    }

    public void removeDeliveryRestaurant(Restaurant restaurant) {
        Delivery delivery = inputObjectHandler.findRestaurantDelivery(viewAdmin, restaurant);
        delivery.removeRestaurant(restaurant);
        restaurant.getDeliveries().remove(delivery);
    }

    public void supermarketTabHandler(Admin admin){

    }


    public void deliveriesTabHandler(Admin admin) {
        viewAdmin.printDeliveriesTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (DeliveriesTabOptions.findOption(userInput)) {
            case ADD_DELIVERY: database.addDelivery();
                break;
            case REMOVE_DELIVERY: database.removeDelivery();
                break;
            case VIEW_EDIT_DELIVERIES: viewAndEditDeliveries(admin);
                break;
            case VIEW_ORDERS: viewDeliveryOrders(admin);
            case EXIT: return;
            default: deliveriesTabHandler(admin);
        }
        deliveriesTabHandler(admin);
    }

    public void viewDeliveryOrders(Admin admin) {
        System.out.println("Which Delivery ?");
        viewAdmin.printDeliveries(database.getDeliveries());
        System.out.println("[" + (database.getDeliveries().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice != database.getDeliveries().size() + 1) {
            viewAdmin.printOrders(database.getDeliveries().get(userChoice - 1).getOrders());
        }
    }

    public void viewAndEditDeliveries(Admin admin) {
        viewAdmin.printDeliveries(database.getDeliveries());
        System.out.println("[" + (database.getDeliveries().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice != database.getDeliveries().size() + 1) {
            editDeliveryHandler(database.getDeliveries().get(userChoice - 1), admin);
        }
    }

    public void editDeliveryHandler(Delivery delivery, Admin admin) {
        viewAdmin.printDeliveryEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (DeliveryEditOptions.findOption(userInput)) {
            case CHANGE_SALARY: database.changeDeliverySalary(delivery);
                break;
            case CHANGE_VEHICLE: database.changeDeliveryVehicle(delivery);
                break;
            case CHANGE_SALARY_TYPE: database.changeDeliverySalaryType(delivery);
                break;
            case EXIT: deliveriesTabHandler(admin);
                break;
            default: editDeliveryHandler(delivery, admin);
        }
        editDeliveryHandler(delivery, admin);
    }

    public void foodTabHandler(Admin admin) {
        viewAdmin.printFoodTab();
        int foodTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (FoodTabOptions.findOption(foodTabInput)) {
            case ADD_FOOD: database.addFood();
                break;
            case REMOVE_FOOD: database.removeFood();
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

    public void viewFoodsHandler() {
        viewAdmin.printFoods(database.getFoods());
    }

    public void viewFoodCommentsHandler() {
        viewAdmin.printFoodComments(inputObjectHandler.selectFood(viewAdmin), database.getRestaurants());
    }
}
