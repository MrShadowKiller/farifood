package ir.ac.kntu.ui;

import ir.ac.kntu.Department;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.objects.Fruit;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.objects.Product;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderStatus;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.SellerMan;
import ir.ac.kntu.user.WeekDays;

import java.util.ArrayList;

public class ViewAdmin {
    public void printAdminStartMenu() {
        System.out.println("\tPlease use of the below options");
        System.out.println("[1].Admins");
        System.out.println("[2].Customers");
        System.out.println("[3].Seller Mans");
        System.out.println("[4].Restaurants");
        System.out.println("[5].SuperMarkets");
        System.out.println("[6].FruitMarkets");
        System.out.println("[7].Food");
        System.out.println("[8].Orders");
        System.out.println("[9].Deliveries");
        System.out.println("[10].Exit");
    }

    public void printAdminsTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add admin");
        System.out.println("[2].Remove admin");
        System.out.println("[3].View Admins and Edit");
        System.out.println("[4].Exit");
    }

    public void printCustomersTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Customer");
        System.out.println("[2].Remove Customer");
        System.out.println("[3].View Customers and Edit");
        System.out.println("[4].View Customer orders");
        System.out.println("[5].View Customer comments");
        System.out.println("[6].Exit");
    }

    public void printSellerManTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add SellerMan");
        System.out.println("[2].Remove SellerMan");
        System.out.println("[3].Add SellerMan to department");
        System.out.println("[4].Exit");
    }

    public void printRestaurantsTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Restaurant");
        System.out.println("[2].Remove Restaurant");
        System.out.println("[3].View and Edit Restaurants");
        System.out.println("[4].View Restaurant orders");
        System.out.println("[5].View Restaurant foods");
        System.out.println("[6].View Restaurant comments");
        System.out.println("[7].View Restaurant Deliveries");
        System.out.println("[8].Exit");
    }

    public void printSuperMarketTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add SuperMarket");
        System.out.println("[2].Remove SuperMarket");
        System.out.println("[3].View and Edit SuperMarket");
        System.out.println("[4].View SuperMarket orders");
        System.out.println("[5].View SuperMarket Products");
        System.out.println("[6].View SuperMarket comments");
        System.out.println("[7].View SuperMarket Deliveries");
        System.out.println("[8].Exit");
    }

    public void printFruitMarketTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add FruitMarket");
        System.out.println("[2].Remove FruitMarket");
        System.out.println("[3].View and Edit FruitMarket");
        System.out.println("[4].View FruitMarket orders");
        System.out.println("[5].View FruitMarket fruits");
        System.out.println("[6].View FruitMarket comments");
        System.out.println("[7].View FruitMarket Deliveries");
        System.out.println("[8].Exit");
    }


    public void printWeekDays() {
        WeekDays[] weekDays = WeekDays.values();
        for (int i = 1; i <= weekDays.length; i++) {
            System.out.println("[" + i + "]. " + weekDays[i - 1].toString());
        }
    }

    public void printAdmins(ArrayList<Admin> admins) {
        for (int i = 1; i <= admins.size(); i++) {
            System.out.println("[" + i + "]. " + admins.get(i - 1).briefInformation());
        }
        System.out.println("[" + (admins.size() + 1) + "]. " + "Exit");
    }

    public void printCustomers(ArrayList<Customer> customers) {
        for (int i = 1; i <= customers.size(); i++) {
            System.out.println("[" + i + "]. " + customers.get(i - 1).toString());
        }
        System.out.println("[" + (customers.size() + 1) + "]. " + "Exit");
    }

    public void printOrders(ArrayList<Order> orders) {
        for (int i = 1; i <= orders.size(); i++) {
            System.out.println("[" + i + "]. " + orders.get(i - 1).toString());
        }
        if (orders.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printAdminEditMenu() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change Personal Information");
        System.out.println("[2].Change Password");
        System.out.println("[3].Change Wallet Balance");
        System.out.println("[4].Exit");
    }

    public void printCustomerEditMenu() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change Personal Information");
        System.out.println("[2].Change Password");
        System.out.println("[3].Change Wallet Balance");
        System.out.println("[4].Exit");
    }

    public void printDeliveriesTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Delivery");
        System.out.println("[2].Remove Delivery");
        System.out.println("[3].View and Edit Deliveries");
        System.out.println("[4].View Orders");
        System.out.println("[5].Exit");
    }

    public void printSalaryTypes() {
        SalaryType[] salaryTypes = SalaryType.values();
        for (int i = 1; i <= salaryTypes.length; i++) {
            System.out.println("[" + i + "]. " + salaryTypes[i - 1].toString());
        }
    }

    public void printRestaurants(ArrayList<Restaurant> restaurants) {
        for (int i = 1; i <= restaurants.size(); i++) {
            System.out.println("[" + i + "]. " + restaurants.get(i - 1).getName());
        }
    }

    public void printDepartments(ArrayList<Department> departments) {
        for (int i = 1; i <= departments.size(); i++) {
            System.out.println("[" + i + "]. " + departments.get(i - 1).getName() + "  " +
                    departments.get(i - 1).getClass());
        }
    }

    public void printDeliveries(ArrayList<Delivery> deliveries) {
        for (int i = 1; i <= deliveries.size(); i++) {
            System.out.println("[" + i + "]. " + deliveries.get(i - 1).getBriefInformation());
            printDeliverySchedule(deliveries.get(i - 1));
        }
    }

    public void printSellerMen(ArrayList<SellerMan> sellerMen) {
        for (int i = 1; i <= sellerMen.size(); i++) {
            System.out.println("[" + i + "]. " + sellerMen.get(i - 1));
        }
    }

    public void printDeliveryEditMenu() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change Salary");
        System.out.println("[2].Change Vehicle Type");
        System.out.println("[3].Change Salary Type");
        System.out.println("[4].Exit");
    }


    public void printDeliveryDepartments(Delivery delivery) {
        for (int i = 1; i <= delivery.getDepartments().length; i++) {
            System.out.println("[" + i + "]. " + delivery.getDepartments()[i - 1].getName());
        }
    }

    public void printDeliverySchedule(Delivery delivery) {
        System.out.println("Weekly Schedule : ");
        for (DeliverySchedule day : delivery.getSchedule()) {
            if (day.getDepartment() != null) {
                System.out.println(day.toString() + ": " + day.getDepartment() + "\n");
            } else {
                if (day.getAvailability()) {
                    System.out.println(day.toString() + ": IS FREE" + "\n");
                } else {
                    System.out.println(day.toString() + ": " + "\n");
                }
            }
        }
    }

    public void printDeliveryVehicles() {
        DeliveryVehicle[] deliveryVehicles = DeliveryVehicle.values();
        for (int i = 1; i <= deliveryVehicles.length; i++) {
            System.out.println("[" + i + "]. " + deliveryVehicles[i - 1].toString());
        }
    }

    public void printFoodTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Food");
        System.out.println("[2].Remove Food");
        System.out.println("[3].View Foods");
        System.out.println("[4].View Food Comments");
        System.out.println("[5].Exit");
    }

    public void printFoods(ArrayList<Food> foods) {
        for (int i = 1; i <= foods.size(); i++) {

            System.out.println("[" + i + "]. " + foods.get(i - 1).getName() +
                    "  " + (foods.get(i - 1)).getTimeForCooking() + "min " +
                    "  " + foods.get(i - 1).getPrice() + "$");
        }
        if (foods.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printItems(ArrayList<Item> items) {
        for (int i = 1; i <= items.size(); i++) {
            System.out.println("[" + i + "]. " + items.get(i - 1).getName() +
                    "  " + items.get(i - 1).getPrice() + "$");
        }
        if (items.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printFoodComments(Food food, ArrayList<Restaurant> restaurants) {
        int count = 1;
        for (Restaurant restaurant : restaurants) {
            for (Comment comment : restaurant.getComments()) {
                for (Item item : comment.getItems() )
                    if (item instanceof Food){
                        if(item.equals(food)) {
                            System.out.println("[" + count + "]. " + comment);
                            count++;
                        }
                    }
            }
        }
        if (count == 1) {
            System.out.println("EMPTY!");
        }
    }

    public void printComments(ArrayList<Comment> comments) {
        for (int i = 1; i <= comments.size(); i++) {
            System.out.println("[" + i + "]. " + comments.get(i - 1));
        }
        if (comments.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printRestaurantTypes() {
        RestaurantType[] restaurantTypes = RestaurantType.values();
        for (int i = 1; i <= restaurantTypes.length; i++) {
            System.out.println("[" + i + "]. " + restaurantTypes[i - 1].toString());
        }
    }

    public void printEditRestaurantTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change name");
        System.out.println("[2].Change Work hours");
        System.out.println("[3].Change Schedule");
        System.out.println("[4].Add Food");
        System.out.println("[5].Remove Food");
        System.out.println("[6].Add Delivery");
        System.out.println("[7].Remove Delivery");
        System.out.println("[8].Exit");
    }

    public void printEditSuperMarketTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change name");
        System.out.println("[2].Add Product");
        System.out.println("[3].Remove Product");
        System.out.println("[4].Add Delivery");
        System.out.println("[5].Remove Delivery");
        System.out.println("[6].Exit");
    }

    public void printEditFruitMarketTab() {
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change name");
        System.out.println("[2].Add Fruit");
        System.out.println("[3].Remove Fruit");
        System.out.println("[4].Add Delivery");
        System.out.println("[5].Remove Delivery");
        System.out.println("[6].Exit");
    }

    public void printRestaurantSchedule(Restaurant restaurant) {
        RestaurantSchedule[] restaurantSchedules = restaurant.getSchedule();
        for (int i = 1; i <= restaurantSchedules.length; i++) {
            if (restaurantSchedules[i - 1].getAvailability()) {
                System.out.println("[" + i + "]. " + restaurantSchedules[i - 1].toString() + " : OPEN");
            } else {
                System.out.println("[" + i + "]. " + restaurantSchedules[i - 1].toString() + " : CLOSE");
            }
        }
    }

    public void printOrderStatus() {
        OrderStatus[] orderStatuses = OrderStatus.values();
        for (int i = 1; i <= orderStatuses.length; i++) {
            System.out.println("[" + i + "]. " + orderStatuses[i - 1].toString());
        }
    }

    public void printOrdersByStatus(ArrayList<Order> orders, OrderStatus orderStatus) {
        int count = 1;
        for (Order order : orders) {
            if (order.getOrderStatus() == orderStatus) {
                System.out.println("[" + count + "]. " + order.toString());
                count++;
            }
        }
    }


    public void printProductsWithSize(ArrayList<Product> products) {
        for (int i = 1; i <= products.size(); i++) {

            System.out.println("[" + i + "]. " + products.get(i - 1).getName() +
                    "  " + products.get(i - 1).getPrice() + "$" + " " + products.get(i - 1).getStock());
        }
        if (products.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printFruits(ArrayList<Fruit> fruits) {
        for (int i = 1; i <= fruits.size(); i++) {

            System.out.println("[" + i + "]. " + fruits.get(i - 1).getName() +
                    "  " + fruits.get(i - 1).getPrice() + "$" + " " + fruits.get(i - 1).getStock());
        }
        if (fruits.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printSuperMarkets(ArrayList<Supermarket> supermarkets) {
        for (int i = 1; i <= supermarkets.size(); i++) {
            System.out.println("[" + i + "]. " + supermarkets.get(i - 1).getName() + "  ");
        }
    }

    public void printFruitMarkets(ArrayList<FruitMarket> fruitMarkets) {
        for (int i = 1; i <= fruitMarkets.size(); i++) {
            System.out.println("[" + i + "]. " + fruitMarkets.get(i - 1).getName());
        }
    }



}
