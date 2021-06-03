package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewCustomer;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Piroozi", "Shakoori St , P8", "20500");
        Admin admin1 = new Admin("Mohammad", "Shahabadi",
                "09216272515", "1", "1", address1);

        InputObjectHandler inputObjectHandler = new InputObjectHandler();
        Database database = new Database(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()
                ,new ArrayList<>(),new ArrayList<>(),new ViewAdmin(),inputObjectHandler,new ViewCustomer());

        inputObjectHandler.setDatabase(database);
        database.getAdmins().add(admin1);
        database.getCustomers().add(admin1);

        Management management = new Management(database,inputObjectHandler);


        while (true) {
            System.out.println("[1].Open Software");
            System.out.println("[2].Enter test values");
            System.out.println("[3].Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == 1) {
                management.startMenu();
            } else if (userChoice == 2) {
                InputObjectHandler inputObjectHandler1 = new InputObjectHandler();
                Database database1 = getTest(admin1,address1);
                management = new Management(database1,inputObjectHandler1);
            } else {
                break;
            }
        }

        ScannerWrapper.getInstance().close();

    }

    public static Database getTest(Admin admin1,Address address1){
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Delivery> deliveries = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        Address address2 = new Address("Baharestan", "Shakoori St , P8", "20500");
        Address address3 = new Address("Valiasr", "Shakoori St , P8", "20500");

        Admin admin2 = new Admin("Ali", "Akbari",
                "09216272515", "1", "1", address1);
        admins.add(admin2);
        customers.add(admin2);

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

        DeliverySchedule[] deliverySchedules2 = DeliverySchedule.values();
        for (int i = 0; i <= deliverySchedules2.length - 3; i++) {
            deliverySchedules2[i].setAvailability(true);
        }


        deliveries.add(new Delivery("Mamad", "esfahani", "09455555", DeliveryVehicle.CAR, SalaryType.HOURLY, 5000, deliverySchedules1));
        deliveries.add(new Delivery("Ali", "esfahani", "09455555", DeliveryVehicle.BIKE, SalaryType.HOURLY, 6000, deliverySchedules2));
        RestaurantSchedule[] schedule1 = RestaurantSchedule.values();
        for (int i = 0; i <= schedule1.length - 3; i++) {
            schedule1[i].setAvailability(true);
        }
        restaurants.add(new Restaurant("Mamad Kababi", address1, 9, 22, schedule1, RestaurantType.ECONOMY));
        restaurants.add(new Restaurant("Ali Kababi", address2, 12, 20, schedule1, RestaurantType.HIGH_CLASS));
        restaurants.add(new Restaurant("Hassan Kababi", address3, 14, 20, schedule1, RestaurantType.LUXURY));

        restaurants.get(0).addFood(new Food(foods.get(0)));
        restaurants.get(1).addFood(new Food(foods.get(0)));
        restaurants.get(2).addFood(new Food(foods.get(0)));
        restaurants.get(0).addFood(new Food(foods.get(1)));
        restaurants.get(1).addFood(new Food(foods.get(1)));
        restaurants.get(2).addFood(new Food(foods.get(1)));
        restaurants.get(0).addFood(new Food(foods.get(2)));
        restaurants.get(1).addFood(new Food(foods.get(2)));
        restaurants.get(2).addFood(new Food(foods.get(2)));

        restaurants.get(0).getFoods().get(0).setPrice(100);
        restaurants.get(1).getFoods().get(0).setPrice(80);
        restaurants.get(2).getFoods().get(0).setPrice(70);

        restaurants.get(0).getFoods().get(1).setPrice(150);
        restaurants.get(1).getFoods().get(1).setPrice(20);
        restaurants.get(2).getFoods().get(1).setPrice(40);

        restaurants.get(0).addDelivery(deliveries.get(0));
        restaurants.get(0).addDelivery(deliveries.get(1));
        restaurants.get(1).addDelivery(deliveries.get(0));
        restaurants.get(1).addDelivery(deliveries.get(1));

        deliveries.get(0).getSchedule()[0].setRestaurant(restaurants.get(0));
        deliveries.get(0).getSchedule()[0].setRestaurant(restaurants.get(1));
        deliveries.get(0).addRestaurant(restaurants.get(0));
        deliveries.get(0).addRestaurant(restaurants.get(1));

        deliveries.get(1).getSchedule()[0].setRestaurant(restaurants.get(0));
        deliveries.get(1).getSchedule()[1].setRestaurant(restaurants.get(1));
        deliveries.get(1).addRestaurant(restaurants.get(0));
        deliveries.get(1).addRestaurant(restaurants.get(1));


        return new Database(admins,restaurants,foods,deliveries,customers,orders,new ViewAdmin()
                ,new InputObjectHandler(),new ViewCustomer());
    }

}