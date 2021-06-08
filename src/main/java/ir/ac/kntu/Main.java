package ir.ac.kntu;

import ir.ac.kntu.Database;
import ir.ac.kntu.logic.Management;
import ir.ac.kntu.models.departments.Department;
import ir.ac.kntu.models.departments.FruitMarket;
import ir.ac.kntu.models.departments.Supermarket;
import ir.ac.kntu.models.objects.*;
import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.delivery.DeliverySchedule;
import ir.ac.kntu.models.delivery.DeliveryVehicle;
import ir.ac.kntu.models.delivery.SalaryType;
import ir.ac.kntu.models.order.Order;
import ir.ac.kntu.models.departments.restaurant.Restaurant;
import ir.ac.kntu.models.departments.restaurant.RestaurantSchedule;
import ir.ac.kntu.models.departments.restaurant.RestaurantType;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.Admin;
import ir.ac.kntu.models.user.Customer;
import ir.ac.kntu.models.user.SellerMan;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Piroozi", "Shakoori St , P8", "20500");
        Admin admin1 = new Admin("Mohammad", "Shahabadi",
                "09216272515", "1", "1", address1);

        Database database = new Database(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ViewAdmin(), new ViewPerson());

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


        // GET TEST OBJECTS FROM TESTOBJECTS FOLDER!


        return new Database(admins, departments, items, deliveries, customers, sellerMEN, orders,
                new ViewAdmin(),new ViewPerson());
    }

}