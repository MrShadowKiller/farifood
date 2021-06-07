package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.management.InputObjectHandler;
import ir.ac.kntu.management.ScannerWrapper;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.Fruit;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.objects.Product;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.sort.*;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewCustomer;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    private ArrayList<Admin> admins;

    private ArrayList<Department> departments;

    private ArrayList<Item> items;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Customer> customers;

    private ArrayList<Order> orders;

    private ViewAdmin viewAdmin;

    private InputObjectHandler inputObjectHandler;

    private ViewCustomer viewCustomer;

    public Database(ArrayList<Admin> admins, ArrayList<Department> departments, ArrayList<Item> items,
                    ArrayList<Delivery> deliveries, ArrayList<Customer> customers, ArrayList<Order> orders,
                    ViewAdmin viewAdmin, InputObjectHandler inputObjectHandler, ViewCustomer viewCustomer) {
        this.admins = admins;
        this.departments = departments;
        this.items = items;
        this.deliveries = deliveries;
        this.customers = customers;
        this.orders = orders;
        this.viewAdmin = viewAdmin;
        this.inputObjectHandler = inputObjectHandler;
        this.viewCustomer = viewCustomer;
    }

    public ArrayList<Admin> getAdmins() {
        return new ArrayList<>(admins);
    }

    public ArrayList<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    public ArrayList<Restaurant> getRestaurants(){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (Department department : departments){
            if (department instanceof Restaurant){
                restaurants.add((Restaurant) department);
            }
        }
        return restaurants;
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    public ArrayList<Food> getFoods(){
        ArrayList<Food> foods = new ArrayList<>();
        for (Item item : items ) {
            if (item instanceof Food){
                foods.add((Food) item);
            }
        }
        return foods;
    }

    public ArrayList<Fruit> getFruits(){
        ArrayList<Fruit> fruits = new ArrayList<>();
        for (Item item : items ) {
            if (item instanceof Fruit){
                fruits.add((Fruit) item);
            }
        }
        return fruits;
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        for (Item item : items ) {
            if (item instanceof Product){
                products.add((Product) item);
            }
        }
        return products;
    }

    public ArrayList<Delivery> getDeliveries() {
        return new ArrayList<>(deliveries);
    }

    public ArrayList<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addAdmin() {
        Admin newAdmin = inputObjectHandler.scanAdminInfo();
        admins.add(newAdmin);
        customers.add(newAdmin);
    }

    public void removeAdmin(){
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

    public void addCustomer(){
        customers.add(inputObjectHandler.scanCustomerInfo());
    }

    public void removeCustomer(){
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

    public void addRestaurant(){
        departments.add(inputObjectHandler.scanRestaurantInfo(viewAdmin));
    }

    public void removeRestaurant() {
        departments.remove(inputObjectHandler.findRestaurant());
    }

    public void addDelivery() {
        deliveries.add(inputObjectHandler.scanDeliveryInfo(viewAdmin));
    }

    public void removeDelivery() {
        deliveries.remove(inputObjectHandler.selectToRemoveDelivery(viewAdmin));
    }

    public void addFood() {
        items.add(inputObjectHandler.scanFoodInfo());
    }

    public void removeFood() {
        items.remove(inputObjectHandler.scanFoodInfo());
    }


    public void changeUserPassword(Customer user) {
        System.out.print("New Password : ");
        String newPassword = ScannerWrapper.getInstance().nextLine().trim();
        if (!user.setPassword(newPassword)){
            System.out.println("Invalid Password!");
        }
    }

    public void changeCustomerBalance(Customer customer) {
        System.out.print("New Balance : ");
        customer.getWallet().setBalance(Double.parseDouble(ScannerWrapper.getInstance().nextLine()));
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

    public void sortRestaurantHighRating() {
        departments.sort(new DepartmentCompareHighRate());
    }

    public void sortRestaurantLowRating() {
        departments.sort(new DepartmentCompareLowRate());
    }

    public void sortRestaurantHighComments() {
        departments.sort(new DepartmentCompareHighComments());
    }

    public void sortRestaurantLowComments() {
        departments.sort(new DepartmentCompareLowComments());
    }

    public void sortRestaurantByRising() {
        departments.sort(new DepartmentCompareRising());
    }



}
