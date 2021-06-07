package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.enums.adminmenu.*;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.Selector;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewCustomer;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

public class MenuHandler {
    private static final MenuHandler INSTANCE = new MenuHandler();

    private AdminService adminService;

    private CustomerService customerService;

    private ViewAdmin viewAdmin;

    private ViewCustomer viewCustomer;

    private Database database;

    private MenuHandler() {
    }

    public static MenuHandler getInstance() {
        return INSTANCE;
    }

    public void adminMenuHandler(Admin admin, ViewAdmin viewAdmin, Database database, AdminService adminService) {
        this.adminService = adminService;
        this.viewAdmin = viewAdmin;
        this.database = database;

        viewAdmin.printAdminStartMenu();
        int adminOptionInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AdminMenuOptions.findOption(adminOptionInput)) {
            case ADMINS -> adminsTabHandler(admin, viewAdmin, database, adminService);
            case CUSTOMERS -> customersTabHandler(admin);
            case DELIVERIES -> deliveriesTabHandler(admin);
            case FOOD -> foodTabHandler(admin);
            case RESTAURANTS -> restaurantsTabHandler(admin);
            case SUPERMARKET -> superMarketTabHandler(admin);
            case FRUITMARKET -> fruitMarketTabHandler(admin);
            case ORDERS -> adminService.ordersTabHandler(admin);
            case EXIT -> {
                return;
            }
            default -> adminMenuHandler(admin, viewAdmin, database, adminService);
        }
        adminMenuHandler(admin, viewAdmin, database, adminService);
    }

    public void adminsTabHandler(Admin admin, ViewAdmin viewAdmin, Database database, AdminService adminService) {
        viewAdmin.printAdminsTab();
        int adminTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AdminsTabOptions.findOption(adminTabInput)) {
            case ADD_ADMIN:
                database.addAdmin();
                break;
            case REMOVE_ADMIN:
                database.removeAdmin();
                break;
            case VIEW_EDIT_ADMIN:
                adminService.viewAndEditAdmins(admin);
                break;
            case EXIT:
                return;
            default:
                adminsTabHandler(admin, viewAdmin, database, adminService);
        }
        adminsTabHandler(admin, viewAdmin, database, adminService);
    }

    public void editAdminHandler(Admin admin) {
        viewAdmin.printAdminEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (AdminEditOptions.findOption(userInput)) {
            case CHANGE_PERSONAL_INFO:
                InputObjectHandler.getInstance().changeCustomerInformation(admin);
                break;
            case CHANGE_PASSWORD:
                database.changeUserPassword(admin);
                break;
            case CHANGE_BALANCE:
                database.changeCustomerBalance(admin);
                break;
            case EXIT:
                return;
            default:
                editAdminHandler(admin);
        }
        editAdminHandler(admin);
    }

    public void customersTabHandler(Admin admin) {
        viewAdmin.printCustomersTab();
        int customerTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (CustomersTabOptions.findOption(customerTabInput)) {
            case ADD_CUSTOMER:
                database.addCustomer();
                break;
            case REMOVE_CUSTOMER:
                database.removeCustomer();
                break;
            case VIEW_EDIT_CUSTOMER:
                adminService.viewAndEditCustomers(admin);
                break;
            case VIEW_CUSTOMER_ORDERS:
                adminService.viewCustomerOrders(admin);
                break;
            case VIEW_COMMENTS:
                adminService.viewCustomerComments(admin);
                break;
            case EXIT:
                return;
            default:
                customersTabHandler(admin);
        }
        customersTabHandler(admin);
    }

    public void editCustomerHandler(Customer customer, Admin admin) {
        viewAdmin.printCustomerEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (CustomerEditOptions.findOption(userInput)) {
            case CHANGE_PERSONAL_INFO:
                InputObjectHandler.getInstance().changeCustomerInformation(customer);
                break;
            case CHANGE_PASSWORD:
                database.changeUserPassword(customer);
                break;
            case CHANGE_BALANCE:
                database.changeCustomerBalance(customer);
                break;
            case EXIT:
                return;
            default:
                editCustomerHandler(customer, admin);
        }
        editCustomerHandler(customer, admin);
    }

    public void restaurantsTabHandler(Admin admin) {
        viewAdmin.printRestaurantsTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (RestaurantsTabOptions.findOption(userInput)) {
            case ADD_RESTAURANT:
                database.addRestaurant();
                break;
            case REMOVE_RESTAURANT:
                database.removeDepartment();
                break;
            case VIEW_EDIT_RESTAURANT:
                viewAndEditRestaurantHandler(admin);
                break;
            case VIEW_ORDERS:
                adminService.viewDepartmentOrders(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_FOODS:
                adminService.viewDepartmentItems(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_COMMENTS:
                adminService.viewDepartmentComments(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_DELIVERIES:
                adminService.viewDepartmentDeliveries(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case EXIT:
                return;
            default:
                restaurantsTabHandler(admin);
        }
        restaurantsTabHandler(admin);
    }

    public void viewAndEditRestaurantHandler(Admin admin) {
        Restaurant selectedRestaurant = Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database);
        if (selectedRestaurant == null) {
            return;
        }
        viewAdmin.printEditRestaurantTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (RestaurantEditOptions.findOption(userInput)) {
            case CHANGE_NAME:
                database.changeDepartmentName(selectedRestaurant);
                break;
            case CHANGE_WORK_HOURS:
                database.changeRestaurantWorkHours(selectedRestaurant);
                break;
            case CHANGE_SCHEDULE:
                database.changeRestaurantSchedule(selectedRestaurant);
                break;
            case ADD_FOOD:
                adminService.addFoodRestaurant(selectedRestaurant);
                break;
            case REMOVE_FOOD:
                adminService.removeFoodRestaurant(selectedRestaurant);
                break;
            case ADD_DELIVERY:
                adminService.addDeliveryRestaurant(selectedRestaurant);
                break;
            case REMOVE_DELIVERY:
                adminService.removeDeliveryDepartment(selectedRestaurant);
                break;
            case EXIT:
                return;
            default:
                viewAndEditRestaurantHandler(admin);
        }
        viewAndEditRestaurantHandler(admin);
    }

    public void superMarketTabHandler(Admin admin) {
        viewAdmin.printSuperMarketTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (SuperMarketTabOptions.findOption(userInput)) {
            case ADD_SUPERMARKET:
                database.addSuperMarket();
                break;
            case REMOVE_SUPERMARKET:
                database.removeDepartment();
                break;
            case VIEW_EDIT_SUPERMARKET:
                viewAndEditSuperMarketHandler(admin);
                break;
            case VIEW_ORDERS:
                adminService.viewDepartmentOrders(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_PRODUCTS:
                adminService.viewDepartmentItems(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_COMMENTS:
                adminService.viewDepartmentComments(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_DELIVERIES:
                adminService.viewDepartmentDeliveries(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case EXIT:
                return;
            default:
                superMarketTabHandler(admin);
        }
        superMarketTabHandler(admin);
    }

    public void viewAndEditSuperMarketHandler(Admin admin) {
        Supermarket selectedSuperMarket = Selector.getInstance().selectSuperMarketHandler(admin, viewAdmin, database);
        if (selectedSuperMarket == null) {
            return;
        }
        viewAdmin.printEditSuperMarketTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (SuperMarketEditOptions.findOption(userInput)) {
            case CHANGE_NAME:
                database.changeDepartmentName(selectedSuperMarket);
                break;
            case ADD_PRODUCT:
                adminService.addProductSuperMarket(selectedSuperMarket);
                break;
            case REMOVE_PRODUCT:
                adminService.removeProductSuperMarket(selectedSuperMarket);
                break;
            case ADD_DELIVERY:
                adminService.addDeliveryDepartment(selectedSuperMarket);
                break;
            case REMOVE_DELIVERY:
                adminService.removeDeliveryDepartment(selectedSuperMarket);
                break;
            case EXIT:
                return;
            default:
                viewAndEditSuperMarketHandler(admin);
        }
        viewAndEditSuperMarketHandler(admin);
    }


    public void fruitMarketTabHandler(Admin admin) {
        viewAdmin.printFruitMarketTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (FruitMarketTabOptions.findOption(userInput)) {
            case ADD_FRUITMARKET:
                database.addFruitMarket();
                break;
            case REMOVE_FRUITMARKET:
                database.removeDepartment();
                break;
            case VIEW_EDIT_FRUITMARKET:
                viewAndEditFruitMarketHandler(admin);
                break;
            case VIEW_ORDERS:
                adminService.viewDepartmentOrders(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_FRUITS:
                adminService.viewDepartmentItems(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_COMMENTS:
                adminService.viewDepartmentComments(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case VIEW_DELIVERIES:
                adminService.viewDepartmentDeliveries(Selector.getInstance().selectRestaurantHandler(admin, viewAdmin, database));
                break;
            case EXIT:
                return;
            default:
                fruitMarketTabHandler(admin);
        }
        fruitMarketTabHandler(admin);
    }

    public void viewAndEditFruitMarketHandler(Admin admin){
        FruitMarket selectedFruitMarket = Selector.getInstance().selectFruitMarketHandler(admin, viewAdmin, database);
        if (selectedFruitMarket == null) {
            return;
        }
        viewAdmin.printEditFruitMarketTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (FruitMarketEditOptions.findOption(userInput)) {
            case CHANGE_NAME:
                database.changeDepartmentName(selectedFruitMarket);
                break;
            case ADD_FRUIT:
                adminService.addFruitFruitMarket(selectedFruitMarket);
                break;
            case REMOVE_FRUIT:
                adminService.removeFruitFruitMarket(selectedFruitMarket);
                break;
            case ADD_DELIVERY:
                adminService.addDeliveryDepartment(selectedFruitMarket);
                break;
            case REMOVE_DELIVERY:
                adminService.removeDeliveryDepartment(selectedFruitMarket);
                break;
            case EXIT:
                return;
            default:
                viewAndEditFruitMarketHandler(admin);
        }
        viewAndEditFruitMarketHandler(admin);
    }

    public void deliveriesTabHandler(Admin admin) {
        viewAdmin.printDeliveriesTab();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (DeliveriesTabOptions.findOption(userInput)) {
            case ADD_DELIVERY:
                database.addDelivery();
                break;
            case REMOVE_DELIVERY:
                database.removeDelivery();
                break;
            case VIEW_EDIT_DELIVERIES:
                adminService.viewAndEditDeliveries(admin);
                break;
            case VIEW_ORDERS:
                adminService.viewDeliveryOrders(admin);
            case EXIT:
                return;
            default:
                deliveriesTabHandler(admin);
        }
        deliveriesTabHandler(admin);
    }

    public void editDeliveryHandler(Delivery delivery, Admin admin) {
        viewAdmin.printDeliveryEditMenu();
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (DeliveryEditOptions.findOption(userInput)) {
            case CHANGE_SALARY:
                database.changeDeliverySalary(delivery);
                break;
            case CHANGE_VEHICLE:
                database.changeDeliveryVehicle(delivery);
                break;
            case CHANGE_SALARY_TYPE:
                database.changeDeliverySalaryType(delivery);
                break;
            case EXIT:
                deliveriesTabHandler(admin);
                break;
            default:
                editDeliveryHandler(delivery, admin);
        }
        editDeliveryHandler(delivery, admin);
    }

    public void foodTabHandler(Admin admin) {
        viewAdmin.printFoodTab();
        int foodTabInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        switch (FoodTabOptions.findOption(foodTabInput)) {
            case ADD_FOOD:
                database.addFood();
                break;
            case REMOVE_FOOD:
                database.removeFood();
                break;
            case VIEW_FOODS:
                adminService.viewFoodsHandler();
                break;
            case VIEW_FOOD_COMMENTS:
                adminService.viewFoodCommentsHandler();
                break;
            case EXIT:
                return;
            default:
                foodTabHandler(admin);
        }
        foodTabHandler(admin);
    }

}
