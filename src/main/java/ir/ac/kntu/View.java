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
        System.out.println("6.Setting");
        System.out.println("7.Exit");
    }

    public void printAdminTab(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Add admin");
        System.out.println("2.Remove admin");
        System.out.println("3.View Admins");
        System.out.println("4.Exit");
    }

    public void printCustomersTab(){
        System.out.println("Please use of these options : ");
        System.out.println("1.Add Customer");
        System.out.println("2.Remove Customer");
        System.out.println("3.View Customers");
        System.out.println("4.View Customer orders");
        System.out.println("5.Exit");
    }

    public void printRestaurantSortOptions(RestaurantSortOption[] restaurantOptions){
        for (int i = 1; i <= restaurantOptions.length; i++) {
            System.out.println("[" + i + "]. " + restaurantOptions[i].getName());
        }
    }

    public void printFoodSortOptions(FoodSortOption[] foodOptions){
        for (int i = 1; i <= foodOptions.length; i++) {
            System.out.println("[" + i + "]. " + foodOptions[i].getName());
        }
    }

    public void printWeekDays(WeekDays[] weekDays){
        for (int i = 1; i <= weekDays.length; i++) {
            System.out.println(i + "." + weekDays[i].toString());
        }
    }

    public void printAdmins(ArrayList<Admin> admins){
        for (int i = 1; i <= admins.size(); i++) {
            System.out.println("[" + i + "]. " + admins.get(i).briefInformation());
        }
    }

    public void printCustomers(ArrayList<Customer> customers){
        for (int i = 1; i <= customers.size(); i++) {
            System.out.println("[" + i + "]. " + customers.get(i).toString());
        }
    }

    public void printCustomerOrders(Customer customer){
        for (int i = 1; i <= customer.getOrders().size(); i++) {
            System.out.println("[" + i + "]. " + customer.getOrders().get(i).toString());
        }
    }

}
