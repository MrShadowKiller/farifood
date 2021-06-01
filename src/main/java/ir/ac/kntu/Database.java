package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.management.InputObjectHandler;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class Database {
    private ArrayList<Admin> admins = new ArrayList<>();

    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    private ArrayList<Food> foods = new ArrayList<>();

    private ArrayList<Delivery> deliveries = new ArrayList<>();

    private ArrayList<Customer> customers = new ArrayList<>();

    private ArrayList<Order> orders = new ArrayList<>();

    private InputObjectHandler inputObjectHandler;

    public void addAdmin() {
        Admin newAdmin = inputObjectHandler.scanAdminInfo();
        admins.add(newAdmin);
        customers.add(newAdmin);
    }

    public void removeAdmin(){
        inputObjectHandler.findAndRemoveAdmin(admins,customers);
    }



}
