package ir.ac.kntu.management;

import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Piroozi", "Shakoori St , P8", "20500");
        Admin admin1 = new Admin("Mohammad", "Shahabadi",
                "09216272515", "1", "1", address1);

        Management management = new Management(admin1);

        while (true) {
            System.out.println("[1].Open Software");
            System.out.println("[2].Enter test values");
            System.out.println("[3].Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == 1) {
                management.startMenu();
            } else if (userChoice == 2) {
                management = getTest(admin1,address1);
            } else {
                break;
            }
        }

        ScannerWrapper.getInstance().close();

    }

    public static Management getTest(Admin admin1,Address address1){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Delivery> deliveries = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        Address address2 = new Address("Baharestan", "Shakoori St , P8", "20500");
        Address address3 = new Address("Valiasr", "Shakoori St , P8", "20500");

        Admin admin2 = new Admin("Ali", "Akbari",
                "09216272515", "1", "1", address1);
        admins.add(admin2);

        foods.add(new Food("Kale pache", 20));
        foods.add(new Food("Kabab", 15));
        foods.add(new Food("Mahi", 10));
        foods.add(new Food("Sobhoone", 8));

        customers.add(new Customer("Kazem", "bikar", "09212222", "kazem", "1", address1));
        customers.add(new Customer("Akbar", "bikar", "0933333", "akbar", "1", address2));
        customers.add(new Customer("Hassan", "bikar", "0933333", "hassan", "1", address3));

        DeliverySchedule[] deliverySchedules1 = DeliverySchedule.values();
        for (int i = 0; i <= deliverySchedules1.length - 3; i++) {
            deliverySchedules1[i].setAvailability(true);
        }
        deliveries.add(new Delivery("Mamad", "esfahani", "09455555", DeliveryVehicle.CAR, SalaryType.HOURLY, 5000, deliverySchedules1));
        deliveries.add(new Delivery("Ali", "esfahani", "09455555", DeliveryVehicle.BIKE, SalaryType.HOURLY, 6000, deliverySchedules1));
        RestaurantSchedule[] schedule1 = RestaurantSchedule.values();
        for (int i = 0; i <= schedule1.length - 3; i++) {
            schedule1[i].setAvailability(true);
        }
        restaurants.add(new Restaurant("Mamad Kababi", address1, 9, 22, schedule1, RestaurantType.ECONOMY));
        restaurants.add(new Restaurant("Ali Kababi", address2, 12, 20, schedule1, RestaurantType.HIGH_CLASS));
        restaurants.add(new Restaurant("Hassan Kababi", address3, 14, 20, schedule1, RestaurantType.LUXURY));
        restaurants.get(0).addFood(foods.get(0));
        restaurants.get(1).addFood(foods.get(0));
        restaurants.get(2).addFood(foods.get(0));
        restaurants.get(0).addFood(foods.get(1));
        restaurants.get(1).addFood(foods.get(1));
        restaurants.get(2).addFood(foods.get(1));
        restaurants.get(0).addFood(foods.get(2));
        restaurants.get(1).addFood(foods.get(2));
        restaurants.get(2).addFood(foods.get(2));

        return new Management(admin1, admins, restaurants, deliveries, customers, foods);
    }

}