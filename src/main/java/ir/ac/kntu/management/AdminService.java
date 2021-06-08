package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.Department;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.Fruit;
import ir.ac.kntu.objects.Product;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderStatus;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.Selector;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.SellerMan;

public class AdminService {

    private final ViewAdmin viewAdmin;

    private final Database database;

    private final AdminMenuHandler adminMenuHandler;

    public AdminService(Database database) {
        this.database = database;
        viewAdmin = new ViewAdmin();
        this.adminMenuHandler = new AdminMenuHandler(this,viewAdmin,database);
    }

    //Start the menu
    public void adminMenuStart(Admin admin) {
        adminMenuHandler.adminMenuTabHandler(admin);
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


    public void viewAndEditAdmins(Admin admin) {
        viewAdmin.printAdmins(database.getAdmins());
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice != database.getAdmins().size() + 1) {
            adminMenuHandler.editAdminHandler(database.getAdmins().get(userChoice - 1));
        }
    }

    public void viewAndEditCustomers(Admin admin) {
        Customer selectedCustomer = Selector.getInstance().selectCustomerHandler(viewAdmin, admin, database);
        if (selectedCustomer != null) {
            adminMenuHandler.editCustomerHandler(selectedCustomer, admin);
        }
    }


    public void viewCustomerOrders(Admin admin) {
        System.out.println("Which Customer ?");
        viewAndEditCustomers(admin);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        viewAdmin.printOrders(database.getCustomers().get(userChoice - 1).getOrders());
    }

    public void viewCustomerComments(Admin admin) {
        viewAdmin.printComments(Selector.getInstance().selectCustomerHandler(viewAdmin, admin, database).getComments());
    }

    public void viewDepartmentOrders(Department department) {
        viewAdmin.printOrders(department.getOrders());
    }

    public void viewDepartmentItems(Department department) {
        viewAdmin.printItems(department.getItems());
    }

    public void viewDepartmentComments(Department department) {
        viewAdmin.printComments(department.getComments());
    }

    public void viewDepartmentDeliveries(Department department) {
        viewAdmin.printDeliveries(department.getDeliveries());
    }

    public void addFoodRestaurant(Restaurant restaurant) {
        Food food = new Food(Selector.getInstance().selectFood(viewAdmin, database.getFoods()));
        System.out.print("price : ");
        double userChoice = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        food.setPrice(userChoice);
        restaurant.addItem(food);
    }

    public void removeFoodRestaurant(Restaurant restaurant) {
        restaurant.getItems().remove(Selector.getInstance().selectFood(viewAdmin, restaurant.getFoods()));
    }

    public void addDeliveryRestaurant(Restaurant restaurant) {
        restaurant.addDelivery(Selector.getInstance().selectRestaurantDelivery(viewAdmin, restaurant, database));
    }

    public void removeDeliveryDepartment(Department department) {
        Delivery delivery = InputObjectHandler.getInstance().findDepartmentDelivery(viewAdmin, department);
        delivery.removeDepartment(department);
        department.getDeliveries().remove(delivery);
    }

    public void addProductSuperMarket(Supermarket supermarket) {
        Product product = new Product(Selector.getInstance().selectProduct(viewAdmin, database.getProducts()));
        System.out.print("price : ");
        double priceChoice = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("Stock : ");
        int stockChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        product.setPrice(priceChoice);
        product.setStock(stockChoice);
        supermarket.addItem(product);
    }

    public void removeProductSuperMarket(Supermarket supermarket) {
        supermarket.getItems().remove(Selector.getInstance().selectProduct(viewAdmin, supermarket.getProducts()));
    }

    public void addFruitFruitMarket(FruitMarket fruitMarket) {
        Fruit fruit = new Fruit(Selector.getInstance().selectFruit(viewAdmin, database.getFruits()));
        System.out.print("price : ");
        double priceChoice = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("Stock : ");
        int stockChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        fruit.setPrice(priceChoice);
        fruit.setStock(stockChoice);
        fruitMarket.addItem(fruit);
    }

    public void removeFruitFruitMarket(FruitMarket fruitMarket) {
        fruitMarket.getItems().remove(Selector.getInstance().selectFruit(viewAdmin, fruitMarket.getFruits()));
    }

    public void addDeliveryDepartment(Department department) {
        department.addDelivery(Selector.getInstance().selectFreeDelivery(database, department));
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
            adminMenuHandler.editDeliveryHandler(database.getDeliveries().get(userChoice - 1), admin);
        }
    }

    public void viewFoodsHandler() {
        viewAdmin.printFoods(database.getFoods());
    }

    public void viewFoodCommentsHandler() {
        viewAdmin.printFoodComments(Selector.getInstance().selectFood(viewAdmin, database.getFoods()), database.getRestaurants());
    }

    public void addSellerManToDepartment(SellerMan sellerMan,Admin admin){
        Department department = Selector.getInstance().selectDepartmentHandler(admin,viewAdmin,database);
        if (department != null){
            department.setSellerMan(sellerMan);
            sellerMan.setDepartment(department);
        }
    }
}
