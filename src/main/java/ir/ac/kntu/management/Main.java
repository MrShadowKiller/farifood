package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.Department;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.objects.*;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.SellerMan;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Piroozi", "Shakoori St , P8", "20500");
        Admin admin1 = new Admin("Mohammad", "Shahabadi",
                "09216272515", "1", "1", address1);

        Database database = new Database(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                , new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ViewAdmin(), new ViewPerson());

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
                Database database1 = getTest(admin1, address1);
                management = new Management(database1);
            } else {
                break;
            }
        }

        ScannerWrapper.getInstance().close();

    }

    public static Database getTest(Admin admin1, Address address1) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Delivery> deliveries = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<SellerMan> sellerMEN = new ArrayList<>();

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

        items.add(new Product("Milk"));
        items.add(new Product("IceCream"));
        items.add(new Product("Rice"));
        items.add(new Product("Meat"));

        items.add(new Fruit("Vegetables"));
        items.add(new Fruit("Cucumber"));
        items.add(new Fruit("Tomato"));
        items.add(new Fruit("Carrot"));

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

        DeliverySchedule[] deliverySchedules3 = DeliverySchedule.values();
        for (int i = 0; i <= deliverySchedules3.length - 3; i++) {
            deliverySchedules3[i].setAvailability(true);
        }

        DeliverySchedule[] deliverySchedules4 = DeliverySchedule.values();
        for (int i = 0; i <= deliverySchedules4.length - 3; i++) {
            deliverySchedules4[i].setAvailability(true);
        }

        DeliverySchedule[] deliverySchedules5 = DeliverySchedule.values();
        for (int i = 0; i <= deliverySchedules5.length - 3; i++) {
            deliverySchedules5[i].setAvailability(true);
        }

        DeliverySchedule[] deliverySchedules6 = DeliverySchedule.values();
        for (int i = 0; i <= deliverySchedules6.length - 3; i++) {
            deliverySchedules6[i].setAvailability(true);
        }


        deliveries.add(new Delivery("Mamad", "esfahani", "09455555", DeliveryVehicle.CAR, SalaryType.HOURLY, 5000, deliverySchedules1));
        deliveries.add(new Delivery("Ali", "esfahani", "09455555", DeliveryVehicle.BIKE, SalaryType.HOURLY, 6000, deliverySchedules2));
        deliveries.add(new Delivery("Mohsen", "Rezaei", "09455555", DeliveryVehicle.CAR, SalaryType.HOURLY, 5000, deliverySchedules3));
        deliveries.add(new Delivery("Ahmad", "Mohammadi", "09455555", DeliveryVehicle.BIKE, SalaryType.HOURLY, 6000, deliverySchedules4));
        deliveries.add(new Delivery("Fariborz", "Aghaei", "09455555", DeliveryVehicle.CAR, SalaryType.HOURLY, 5000, deliverySchedules5));
        deliveries.add(new Delivery("Sina", "Sarbaz", "09455555", DeliveryVehicle.BIKE, SalaryType.HOURLY, 6000, deliverySchedules6));
        RestaurantSchedule[] schedule1 = RestaurantSchedule.values();
        for (int i = 0; i <= schedule1.length - 3; i++) {
            schedule1[i].setAvailability(true);
        }

        departments.add(new Restaurant("Mamad Kababi", address1, 9, 22, schedule1, RestaurantType.ECONOMY));
        departments.add(new Restaurant("Ali Kababi", address2, 12, 20, schedule1, RestaurantType.HIGH_CLASS));
        departments.add(new Restaurant("Hassan Kababi", address3, 14, 20, schedule1, RestaurantType.LUXURY));
        departments.add(new Supermarket("DINA Store", address1, 9, 22));
        departments.add(new Supermarket("Kourosh Store", address2, 12, 20));
        departments.add(new Supermarket("Ziba Store", address3, 14, 20));
        departments.add(new FruitMarket("Akbar Market", address1, 9, 22));
        departments.add(new FruitMarket("Hasan Market", address2, 12, 20));
        departments.add(new FruitMarket("Reza Market", address3, 14, 20));

        departments.get(0).addItem(new Food((Food) items.get(0)));
        departments.get(1).addItem(new Food((Food) items.get(0)));
        departments.get(2).addItem(new Food((Food) items.get(0)));
        departments.get(0).addItem(new Food((Food) items.get(1)));
        departments.get(1).addItem(new Food((Food) items.get(1)));
        departments.get(2).addItem(new Food((Food) items.get(1)));
        departments.get(0).addItem(new Food((Food) items.get(2)));
        departments.get(1).addItem(new Food((Food) items.get(2)));
        departments.get(2).addItem(new Food((Food) items.get(2)));

        departments.get(3).addItem(new Product((Product) items.get(4),20));
        departments.get(4).addItem(new Product((Product) items.get(4),1));
        departments.get(5).addItem(new Product((Product) items.get(4),0));
        departments.get(3).addItem(new Product((Product) items.get(5),10));
        departments.get(4).addItem(new Product((Product) items.get(5),3));
        departments.get(5).addItem(new Product((Product) items.get(5),4));
        departments.get(3).addItem(new Product((Product) items.get(6),10));
        departments.get(4).addItem(new Product((Product) items.get(6),9));
        departments.get(5).addItem(new Product((Product) items.get(6),5));

        departments.get(6).addItem(new Fruit((Fruit) items.get(8),20));
        departments.get(7).addItem(new Fruit((Fruit) items.get(8),0));
        departments.get(8).addItem(new Fruit((Fruit) items.get(8),10));
        departments.get(6).addItem(new Fruit((Fruit) items.get(9),4));
        departments.get(7).addItem(new Fruit((Fruit) items.get(9),1));
        departments.get(8).addItem(new Fruit((Fruit) items.get(9),10));
        departments.get(6).addItem(new Fruit((Fruit) items.get(10),21));
        departments.get(7).addItem(new Fruit((Fruit) items.get(10),3));
        departments.get(8).addItem(new Fruit((Fruit) items.get(10),4));

        ((Restaurant) (departments.get(0))).getFoods().get(0).setPrice(100);
        ((Restaurant) (departments.get(1))).getFoods().get(0).setPrice(80);
        ((Restaurant) (departments.get(2))).getFoods().get(0).setPrice(70);
        ((Restaurant) (departments.get(0))).getFoods().get(1).setPrice(150);
        ((Restaurant) (departments.get(1))).getFoods().get(1).setPrice(20);
        ((Restaurant) (departments.get(2))).getFoods().get(1).setPrice(40);

        ((Supermarket) (departments.get(3))).getProducts().get(0).setPrice(100);
        ((Supermarket) (departments.get(4))).getProducts().get(0).setPrice(80);
        ((Supermarket) (departments.get(5))).getProducts().get(0).setPrice(70);
        ((Supermarket) (departments.get(3))).getProducts().get(1).setPrice(150);
        ((Supermarket) (departments.get(4))).getProducts().get(1).setPrice(20);
        ((Supermarket) (departments.get(5))).getProducts().get(1).setPrice(40);

        ((FruitMarket) (departments.get(6))).getFruits().get(0).setPrice(100);
        ((FruitMarket) (departments.get(7))).getFruits().get(0).setPrice(80);
        ((FruitMarket) (departments.get(8))).getFruits().get(0).setPrice(70);
        ((FruitMarket) (departments.get(6))).getFruits().get(1).setPrice(150);
        ((FruitMarket) (departments.get(7))).getFruits().get(1).setPrice(20);
        ((FruitMarket) (departments.get(8))).getFruits().get(1).setPrice(40);

        departments.get(0).addDelivery(deliveries.get(0));
        departments.get(0).addDelivery(deliveries.get(1));
        departments.get(1).addDelivery(deliveries.get(0));
        departments.get(1).addDelivery(deliveries.get(1));

        deliveries.get(0).getSchedule()[0].setDepartment( departments.get(0));
        deliveries.get(0).getSchedule()[0].setDepartment(departments.get(1));
        deliveries.get(0).addDepartment(departments.get(0));
        deliveries.get(0).addDepartment(departments.get(1));

        deliveries.get(1).getSchedule()[0].setDepartment(departments.get(0));
        deliveries.get(1).getSchedule()[1].setDepartment(departments.get(2));
        deliveries.get(1).addDepartment(departments.get(0));
        deliveries.get(1).addDepartment(departments.get(1));

        //SuperMarket DELIVERIES
        departments.get(3).addDelivery(deliveries.get(2));
        departments.get(3).addDelivery(deliveries.get(3));
        departments.get(4).addDelivery(deliveries.get(2));
        departments.get(4).addDelivery(deliveries.get(3));


        deliveries.get(2).getSchedule()[0].setDepartment( departments.get(3));
        deliveries.get(2).getSchedule()[0].setDepartment(departments.get(4));
        deliveries.get(2).addDepartment(departments.get(3));
        deliveries.get(2).addDepartment(departments.get(4));

        deliveries.get(3).getSchedule()[0].setDepartment(departments.get(3));
        deliveries.get(3).getSchedule()[0].setDepartment(departments.get(4));
        deliveries.get(3).addDepartment(departments.get(3));
        deliveries.get(3).addDepartment(departments.get(4));

        //FruitMarket Deliveries
        departments.get(6).addDelivery(deliveries.get(4));
        departments.get(6).addDelivery(deliveries.get(5));
        departments.get(7).addDelivery(deliveries.get(4));
        departments.get(7).addDelivery(deliveries.get(5));

        deliveries.get(4).getSchedule()[0].setDepartment( departments.get(6));
        deliveries.get(4).getSchedule()[0].setDepartment(departments.get(7));
        deliveries.get(4).addDepartment(departments.get(6));
        deliveries.get(4).addDepartment(departments.get(7));

        deliveries.get(5).getSchedule()[0].setDepartment(departments.get(6));
        deliveries.get(5).getSchedule()[0].setDepartment(departments.get(7));
        deliveries.get(5).addDepartment(departments.get(6));
        deliveries.get(5).addDepartment(departments.get(7));


        SellerMan sellerMan1 = new SellerMan("2","2","Seyed", "SSS", "09216272515");
        sellerMan1.setDepartment(departments.get(0));
        departments.get(0).setSellerMan(sellerMan1);

        SellerMan sellerMan2 = new SellerMan("3", "3",
                "09216272515", "1", "1");
        sellerMan2.setDepartment(departments.get(3));
        departments.get(3).setSellerMan(sellerMan2);


        SellerMan sellerMan3 = new SellerMan("4", "4",
                "09216272515", "1", "1");
        sellerMan3.setDepartment(departments.get(6));
        departments.get(3).setSellerMan(sellerMan3);


        sellerMEN.add(sellerMan1);
        sellerMEN.add(sellerMan2);
        sellerMEN.add(sellerMan3);


        return new Database(admins, departments, items, deliveries, customers,sellerMEN,orders
        ,new ViewAdmin(),new ViewPerson());
    }

}