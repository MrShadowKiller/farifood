package ir.ac.kntu;

import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class View {
    public void printStartMenu() {
        System.out.println("Welcome to Farifood Food Service");
        System.out.println("1.Admin login");
        System.out.println("2.Customer login");
        System.out.println("3.Exit");
    }

    public void printAdminStartMenu() {
        System.out.println("\tPlease use of the below options");
        System.out.println("1.Admins");
        System.out.println("2.Customers");
        System.out.println("3.Restaurants");
        System.out.println("4.Food");
        System.out.println("5.Orders");
        System.out.println("6.Deliveries");
        System.out.println("7.Exit");
    }

    public void printAdminsTab(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Add admin");
        System.out.println("2.Remove admin");
        System.out.println("3.View Admins and Edit");
        System.out.println("4.Exit");
    }

    public void printCustomersTab(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Add Customer");
        System.out.println("2.Remove Customer");
        System.out.println("3.View Customers and Edit");
        System.out.println("4.View Customer orders");
        System.out.println("5.Exit");
    }

    public void printRestaurantsTab(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Add Restaurant");
        System.out.println("2.Remove Restaurant");
        System.out.println("3.View Restaurants");
        System.out.println("4.View Restaurant orders");
        System.out.println("5.Exit");
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

    public void printWeekDays(WeekDays[] weekDays){
        for (int i = 1; i <= weekDays.length; i++) {
            System.out.println(i + "." + weekDays[i-1].toString());
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

    public void printCustomerOrders(Customer customer){
        for (int i = 1; i <= customer.getOrders().size(); i++) {
            System.out.println("[" + i + "]. " + customer.getOrders().get(i-1).toString());
        }
        if (customer.getOrders().size() == 0){
            System.out.println("EMPTY!");
        }
    }

    public void printAdminEditMenu(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Change Personal Information");
        System.out.println("2.Change Password");
        System.out.println("3.Change Wallet Balance");
        System.out.println("4.Exit");
    }

    public void printCustomerEditMenu(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Change Personal Information");
        System.out.println("2.Change Password");
        System.out.println("3.Change Wallet Balance");
        System.out.println("4.Exit");
    }

}
