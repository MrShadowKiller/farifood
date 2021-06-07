package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.Department;
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
import ir.ac.kntu.objects.Item;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Piroozi", "Shakoori St , P8", "20500");
        Admin admin1 = new Admin("Mohammad", "Shahabadi",
                "09216272515", "1", "1", address1);

        Database database = new Database(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()
                ,new ArrayList<>(),new ArrayList<>(),new ViewAdmin(),new ViewCustomer());

        database.getAdmins().add(admin1);
        database.getCustomers().add(admin1);

        Management management = new Management(database);


        while (true) {
            System.out.println("[1].Open Software");
            System.out.println("[2].Enter test values");
            System.out.println("[3].Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == 1) {
                management.startMenu();
            } else if (userChoice == 2) {
                Database database1 = getTest(admin1,address1);
                management = new Management(database1);
            } else {
                break;
            }
        }

        ScannerWrapper.getInstance().close();

    }

    public static Database getTest(Admin admin1,Address address1){
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Delivery> deliveries = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        Address address2 = new Address("Baharestan", "Shakoori St , P8", "20500");
        Address address3 = new Address("Valiasr", "Shakoori St , P8", "20500");

        Admin admin2 = new Admin("Ali", "Akbari",
                "09216272515", "1", "1", address1);
        admins.add(admin2);
        customers.add(admin2);

        items.add(new Food("Kale pache", 20));
        items.add(new Food("Kabab", 15));
        items.add(new Food("Mahi", 10));
        items.add(new Food("Sobhoone", 8));

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

        departments.add(new Restaurant("Mamad Kababi", address1, 9, 22, schedule1, RestaurantType.ECONOMY));
        departments.add(new Restaurant("Ali Kababi", address2, 12, 20, schedule1, RestaurantType.HIGH_CLASS));
        departments.add(new Restaurant("Hassan Kababi", address3, 14, 20, schedule1, RestaurantType.LUXURY));

        departments.get(0).addItem(new Food((Food)items.get(0)));
        departments.get(1).addItem(new Food((Food)items.get(0)));
        departments.get(2).addItem(new Food((Food)items.get(0)));
        departments.get(0).addItem(new Food((Food)items.get(1)));
        departments.get(1).addItem(new Food((Food)items.get(1)));
        departments.get(2).addItem(new Food((Food)items.get(1)));
        departments.get(0).addItem(new Food((Food)items.get(2)));
        departments.get(1).addItem(new Food((Food)items.get(2)));
        departments.get(2).addItem(new Food((Food)items.get(2)));

        departments.get(0).getItems().get(0).setPrice(100);
        ((Restaurant)(departments.get(1))).getFoods().get(0).setPrice(80);
        ((Restaurant)(departments.get(2))).getFoods().get(0).setPrice(70);

        ((Restaurant)(departments.get(0))).getFoods().get(1).setPrice(150);
        ((Restaurant)(departments.get(1))).getFoods().get(1).setPrice(20);
        ((Restaurant)(departments.get(2))).getFoods().get(1).setPrice(40);

        departments.get(0).addDelivery(deliveries.get(0));
        departments.get(0).addDelivery(deliveries.get(1));
        departments.get(1).addDelivery(deliveries.get(0));
        departments.get(1).addDelivery(deliveries.get(1));

        deliveries.get(0).getSchedule()[0].setRestaurant((Restaurant) (departments.get(0)));
        deliveries.get(0).getSchedule()[0].setRestaurant((Restaurant) (departments.get(1)));
        deliveries.get(0).addRestaurant((Restaurant) (departments.get(0)));
        deliveries.get(0).addRestaurant((Restaurant) (departments.get(1)));

        deliveries.get(1).getSchedule()[0].setRestaurant((Restaurant) (departments.get(0)));
        deliveries.get(1).getSchedule()[1].setRestaurant((Restaurant) (departments.get(2)));
        deliveries.get(1).addRestaurant((Restaurant) (departments.get(0)));
        deliveries.get(1).addRestaurant((Restaurant) (departments.get(1)));


        return new Database(admins,departments,items,deliveries,customers,orders,new ViewAdmin()
               ,new ViewCustomer());
    }

}