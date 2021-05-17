package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.order.OrderStatus;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import java.util.ArrayList;

public class View {
    public void printStartMenu() {
        System.out.println("Welcome to Farifood Food Service");
        System.out.println("[1].Admin login");
        System.out.println("[2].Customer login");
        System.out.println("[3].Exit");
    }

    public void printAdminStartMenu() {
        System.out.println("\tPlease use of the below options");
        System.out.println("[1].Admins");
        System.out.println("[2].Customers");
        System.out.println("[3].Restaurants");
        System.out.println("[4].Food");
        System.out.println("[5].Orders");
        System.out.println("[6].Deliveries");
        System.out.println("[7].Exit");
    }

    public void printAdminsTab(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add admin");
        System.out.println("[2].Remove admin");
        System.out.println("[3].View Admins and Edit");
        System.out.println("[4].Exit");
    }

    public void printCustomersTab(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Customer");
        System.out.println("[2].Remove Customer");
        System.out.println("[3].View Customers and Edit");
        System.out.println("[4].View Customer orders");
        System.out.println("[5].View Customer comments");
        System.out.println("[5].Exit");
    }

    public void printRestaurantsTab(){
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

    public void printRestaurantSortOptions(RestaurantSortOption[] restaurantOptions){
        for (int i = 1; i <= restaurantOptions.length; i++) {
            System.out.println("[" + i + "]. " + restaurantOptions[i-1].getName());
        }
    }

    public void printFoodSortOptions(FoodSortOption[] foodOptions){
        for (int i = 1; i <= foodOptions.length; i++) {
            System.out.println("[" + i + "]. " + foodOptions[i-1].getName());
        }
    }

    public void printWeekDays(){
        WeekDays[] weekDays = WeekDays.values();
        for (int i = 1; i <= weekDays.length; i++) {
            System.out.println("[" + i + "]. " + weekDays[i-1].toString());
        }
    }

    public void printAdmins(ArrayList<Admin> admins){
        for (int i = 1; i <= admins.size(); i++) {
            System.out.println("[" + i + "]. " + admins.get(i-1).briefInformation());
        }
        System.out.println("[" + (admins.size()+1) + "]. " + "Exit");
    }

    public void printCustomers(ArrayList<Customer> customers){
        for (int i = 1; i <= customers.size(); i++) {
            System.out.println("[" + i + "]. " + customers.get(i-1).toString());
        }
        System.out.println("[" + (customers.size()+1) + "]. " + "Exit");
    }

    public void printOrders(ArrayList<Order> orders){
        for (int i = 1; i <= orders.size(); i++) {
            System.out.println("[" + i + "]. " + orders.get(i-1).toString());
        }
        if (orders.size() == 0){
            System.out.println("EMPTY!");
        }
    }

    public void printAdminEditMenu(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change Personal Information");
        System.out.println("[2].Change Password");
        System.out.println("[3].Change Wallet Balance");
        System.out.println("[4].Exit");
    }

    public void printCustomerEditMenu(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change Personal Information");
        System.out.println("[2].Change Password");
        System.out.println("[3].Change Wallet Balance");
        System.out.println("[4].Exit");
    }

    public void printDeliveriesTab(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Delivery");
        System.out.println("[2].Remove Delivery");
        System.out.println("[3].View and Edit Deliveries");
        System.out.println("[4].View Orders");
        System.out.println("[5].Exit");
    }

    public void printSalaryTypes(){
        SalaryType[] salaryTypes = SalaryType.values();
        for (int i = 1; i <= salaryTypes.length; i++) {
            System.out.println("[" + i + "]. " + salaryTypes[i-1].toString());
        }
    }

    public void printRestaurants(ArrayList<Restaurant> restaurants){
        for (int i=1;i<=restaurants.size();i++){
            System.out.println("["+ i +"]. " + restaurants.get(i-1).getName());
        }
    }

    public void printDeliveries(ArrayList<Delivery> deliveries){
        for (int i=1;i<=deliveries.size();i++){
            System.out.println("["+ i +"]. " + deliveries.get(i-1).getBriefInformation());
            printDeliverySchedule(deliveries.get(i-1));
        }
    }

    public void printDeliveryEditMenu(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Change Salary");
        System.out.println("[2].Change Vehicle Type");
        System.out.println("[3].Change Restaurants of Delivery");
        System.out.println("[4].Exit");
    }



    public void printDeliveryRestaurants(Delivery delivery){
        for (int i=1;i<=delivery.getRestaurants().length;i++){
            System.out.println("[" + i + "]. " +  delivery.getRestaurants()[i-1].getName());
        }
    }

    public void printDeliverySchedule(Delivery delivery){
        System.out.println("Weekly Schedule : ");
        for (DeliverySchedule day : delivery.getSchedule()){
            if (day.getRestaurant() != null){
                System.out.println(day.toString() + ": " + day.getRestaurant() + "\n");
            }else {
                if (day.getAvailability()) {
                    System.out.println(day.toString() + ": IS FREE" + "\n");
                } else {
                    System.out.println(day.toString() + ": " + "\n");
                }
            }
        }
    }

    public void printDeliveryVehicles(){
        DeliveryVehicle[] deliveryVehicles = DeliveryVehicle.values();
        for (int i = 1; i <= deliveryVehicles.length; i++) {
            System.out.println("[" + i + "]. " + deliveryVehicles[i-1].toString());
        }
    }

    public void printFoodTab(){
        System.out.println("Please use of these options : ");
        System.out.println("[1].Add Food");
        System.out.println("[2].Remove Food");
        System.out.println("[3].View Foods");
        System.out.println("[4].View Food Comments");
        System.out.println("[5].Exit");
    }

    public void printFoods(ArrayList<Food> foods){
        for (int i=1;i<=foods.size();i++){
            System.out.println("["+ i +"]. " + foods.get(i-1).getName() +
                    "  " + foods.get(i-1).getTimeForCooking() + "min " +
                    "  " + foods.get(i-1).getPrice() + "$"  );
        }
        if (foods.size()==0){
            System.out.println("EMPTY!");
        }
    }

    public void printFoodComments(Food food,ArrayList<Restaurant> restaurants){
        int count = 1;
        for (Restaurant restaurant : restaurants){
            for (Comment comment : restaurant.getComments()){
                if (comment.getFood().equals(food)){
                    System.out.println("["+ count +"]. " + comment);
                    count++;
                }
            }
        }
        if (count == 1){
            System.out.println("EMPTY!");
        }
    }

    public void printComments(ArrayList<Comment> comments){
        for (int i=1;i<=comments.size();i++){
            System.out.println("[" + i + "]. " +  comments.get(i-1));
        }
        if (comments.size() == 0){
            System.out.println("EMPTY!");
        }
    }

    public void printRestaurantTypes(){
        RestaurantType[] restaurantTypes = RestaurantType.values();
        for (int i = 1; i <= restaurantTypes.length; i++) {
            System.out.println("[" + i + "]. " + restaurantTypes[i-1].toString());
        }
    }

    public void printEditRestaurantTab(){
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

    public void printRestaurantSchedule(Restaurant restaurant){
        RestaurantSchedule[] restaurantSchedules = restaurant.getSchedule();
        for (int i = 1; i <= restaurantSchedules.length; i++) {
            if (restaurantSchedules[i - 1].getAvailability()){
                System.out.println("[" + i + "]. " + restaurantSchedules[i-1].toString() + " : OPEN");
            } else {
                System.out.println("[" + i + "]. " + restaurantSchedules[i-1].toString() + " : CLOSE");
            }
        }
    }

    public void printOrderStatus(){
        OrderStatus[] orderStatuses = OrderStatus.values();
        for (int i = 1; i <= orderStatuses.length; i++) {
            System.out.println("[" + i + "]. " + orderStatuses[i-1].toString());
        }
    }

    public void printOrdersByStatus(ArrayList<Order> orders,OrderStatus orderStatus){
        int count = 1;
        for (Order order : orders){
            if (order.getOrderStatus() == orderStatus){
                System.out.println("[" + count + "]. " + order.toString());
                count++;
            }
        }
    }

    public void printCustomerMenu(){
        System.out.println("\tPlease use of the below options");
        System.out.println("[1].Buy Food");
        System.out.println("[2].Edit Information");
        System.out.println("[3].Show Information");
        System.out.println("[4].Add Balance");
        System.out.println("[5].Setting");
        System.out.println("[6].Exit");
    }

    public void printCustomerBuyFoodTab(){
        System.out.println("[1].Show Restaurants (By User Setting)");
        System.out.println("[2].Show Best 3 Restaurants and their Foods");
        System.out.println("[3].Show Restaurants with the best Food");
        System.out.println("[4].Search Restaurant By Name");
        System.out.println("[5].Search Restaurant By Type");
        System.out.println("[7].Exit");
    }
}
