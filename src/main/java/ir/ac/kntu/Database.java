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
import ir.ac.kntu.restaurant.Selector;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewCustomer;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.SellerMan;

import java.util.ArrayList;

public class Database {
    private ArrayList<Admin> admins;

    private ArrayList<Customer> customers;

    private ArrayList<SellerMan> sellerMen;

    private ArrayList<Department> departments;

    private ArrayList<Item> items;

    private ArrayList<Delivery> deliveries;

    private ArrayList<Order> orders;

    private ViewAdmin viewAdmin;

    private ViewCustomer viewCustomer;

    public Database(ArrayList<Admin> admins, ArrayList<Department> departments, ArrayList<Item> items,
                    ArrayList<Delivery> deliveries, ArrayList<Customer> customers,ArrayList<SellerMan> sellerMen, ArrayList<Order> orders,
                    ViewAdmin viewAdmin, ViewCustomer viewCustomer) {
        this.admins = admins;
        this.departments = departments;
        this.items = items;
        this.deliveries = deliveries;
        this.customers = customers;
        this.orders = orders;
        this.viewAdmin = viewAdmin;
        this.viewCustomer = viewCustomer;
        this.sellerMen = sellerMen;
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

    public ArrayList<SellerMan> getSellerMen() {
        return sellerMen;
    }

    public ArrayList<Supermarket> getSuperMarkets(){
        ArrayList<Supermarket> supermarkets = new ArrayList<>();
        for (Department department : departments){
            if (department instanceof Supermarket){
                supermarkets.add((Supermarket) department);
            }
        }
        return supermarkets;
    }

    public ArrayList<FruitMarket> getFruitMarkets(){
        ArrayList<FruitMarket> fruitMarkets = new ArrayList<>();
        for (Department department : departments){
            if (department instanceof FruitMarket){
                fruitMarkets.add((FruitMarket) department);
            }
        }
        return fruitMarkets;
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
        Admin newAdmin = InputObjectHandler.getInstance().scanAdminInfo();
        admins.add(newAdmin);
        customers.add(newAdmin);
    }

    public void removeAdmin(){
        String[] adminLoginDetails = InputObjectHandler.getInstance().scanCustomerLogin();
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
        customers.add(InputObjectHandler.getInstance().scanCustomerInfo());
    }

    public void removeCustomer(){
        String[] customerLoginDetails = InputObjectHandler.getInstance().scanCustomerLogin();
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

    public void addSellerMan(){
        sellerMen.add(InputObjectHandler.getInstance().scanSellerManInfo());
    }

    public void removeSellerMan(){
        SellerMan sellerMan = Selector.getInstance().selectSellerMan(viewAdmin,this);
        sellerMen.remove(sellerMan);
        for (Department department : departments){
            if (department.getSellerMan().equals(sellerMan)){
                department.setSellerMan(null);
            }
        }
    }
    public void addRestaurant(){
        departments.add(InputObjectHandler.getInstance().scanRestaurantInfo(viewAdmin,this));
    }

    public void addSuperMarket(){
        departments.add(InputObjectHandler.getInstance().scanSuperMarketInfo(viewAdmin,this));
    }

    public void addFruitMarket(){
        departments.add(InputObjectHandler.getInstance().scanSuperMarketInfo(viewAdmin,this));
    }

    public void removeDepartment(){
        departments.remove(InputObjectHandler.getInstance().findDepartment(this));
    }

    public void addDelivery() {
        deliveries.add(InputObjectHandler.getInstance().scanDeliveryInfo(viewAdmin));
    }

    public void removeDelivery() {
        deliveries.remove(Selector.getInstance().selectToRemoveDelivery(viewAdmin,this));
    }

    public void addFood() {
        items.add(InputObjectHandler.getInstance().scanFoodInfo());
    }

    public void removeFood() {
        items.remove(InputObjectHandler.getInstance().scanFoodInfo());
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

    public void changeDepartmentName(Department department) {
        System.out.print("New Name : ");
        department.setName(ScannerWrapper.getInstance().nextLine().trim());
    }

    public void changeRestaurantWorkHours(Restaurant restaurant) {
        Selector.getInstance().selectRestaurantWorkHours(restaurant);
    }

    public void changeRestaurantSchedule(Restaurant restaurant) {
        System.out.println("Which days restaurant is available ? ");
        restaurant.setSchedule(Selector.getInstance().selectRestaurantSchedule(viewAdmin));
    }

    public void changeDeliverySalary(Delivery delivery) {
        System.out.println("How Much ? ");
        delivery.setSalary(Double.parseDouble(ScannerWrapper.getInstance().nextLine()));
    }

    public void changeDeliveryVehicle(Delivery delivery) {
        delivery.setVehicleType(Selector.getInstance().selectDeliveryVehicle());
    }

    public void changeDeliverySalaryType(Delivery delivery) {
        delivery.setSalaryType(Selector.getInstance().selectDeliverySalaryType());
    }

    public void sortRestaurantHighRating() {
        departments.sort((o1,o2) -> Double.compare(o2.getAverageRate(),o1.getAverageRate()));
    }

    public void sortRestaurantLowRating() {
        departments.sort((o1,o2) -> Double.compare(o1.getAverageRate(),o2.getAverageRate()));
    }


    public void sortRestaurantHighComments() {
        departments.sort((o1,o2) -> Integer.compare(o2.getComments().size(),o1.getComments().size()));
    }

    public void sortRestaurantLowComments() {
        departments.sort((o1,o2) -> Integer.compare(o1.getComments().size(),o2.getComments().size()));
    }

    public void sortRestaurantByRising() {
        departments.sort((o1, o2) -> {
            if (o1.getOrders().size() >= o2.getOrders().size() && o2.getAverageRate() >= 3) {
                return 1;
            } else {
                return -1;
            }
        });
    }



}
