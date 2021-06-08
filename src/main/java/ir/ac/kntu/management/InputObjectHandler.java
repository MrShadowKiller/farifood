package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.Department;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.objects.*;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.restaurant.Selector;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.setting.UserSetting;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.user.*;

import java.util.ArrayList;

public class InputObjectHandler {
    private static final InputObjectHandler INSTANCE = new InputObjectHandler();
    private InputObjectHandler(){

    }

    public static InputObjectHandler getInstance(){
        return INSTANCE;
    }

    public Admin scanAdminInfo() {
        System.out.println("\tPlease enter the required information");
        System.out.print("username: ");
        String username = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("password: ");
        String password = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("first name: ");
        String firstname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("last name: ");
        String lastname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Address Section\nneighbor: ");
        Address address = scanAddressInfo();

        return new Admin(firstname, lastname, phoneNumber,
                username, password, address);
    }

    public String[] scanCustomerLogin() {
        String[] loginDetails = new String[2];
        System.out.println("\tPlease enter the required information");
        System.out.print("username: ");
        loginDetails[0] = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("password: ");
        loginDetails[1] = ScannerWrapper.getInstance().nextLine().trim();
        return loginDetails;
    }

    public Customer scanCustomerInfo() {
        System.out.println("\tPlease enter the required information");
        System.out.print("username: ");
        String username = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("password: ");
        String password = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("first name: ");
        String firstname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("last name: ");
        String lastname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("\tAddress Section\nneighbor: ");
        Address address = scanAddressInfo();
        System.out.println("Want to add Credit Card for faster transactions ?");
        String creditCardChoice = ScannerWrapper.getInstance().nextLine().trim();

        Customer resultCustomer = new Customer(firstname, lastname, phoneNumber,
                username, password, address);

        if (creditCardChoice.matches("[Yy](es)*")) {
            resultCustomer.setCreditCard(scanCreditCard());
        }

        return resultCustomer;
    }

    public CreditCard scanCreditCard() {
        System.out.print("Credit Card Number: ");
        String creditCardNumber = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Credit Card Password: ");
        String creditCardPassword = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Balance: ");
        double creditCardBalance = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        return new CreditCard(creditCardNumber, creditCardPassword, creditCardBalance);
    }

    public void changeCustomerInformation(Customer customer) {
        System.out.println("\tPlease enter the required information");
        System.out.print("first name: ");
        customer.setFirstName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("last name: ");
        customer.setLastName(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("phone number: ");
        customer.setPhoneNumber(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("\tAddress Section\nneighbor: ");
        Address address = scanAddressInfo();
        customer.setAddress(address);
    }

    public Address scanAddressInfo() {
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("full address: ");
        String fullAddress = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("zip code: ");
        String zipcode = ScannerWrapper.getInstance().nextLine().trim();
        return new Address(neighbor, fullAddress, zipcode);
    }

    public UserSetting scanUserSetting(ViewPerson viewPerson) {
        System.out.println("How do you want the foods to be sorted ?");
        FoodSortOption foodSortOption = Selector.getInstance().selectFoodSort(viewPerson);
        System.out.println("How do you want the restaurants to be sorted ?");
        RestaurantSortOption restaurantSortOption = Selector.getInstance().selectRestaurantSort(viewPerson);
        System.out.println("How do you want to choose your current Day ?");
        WeekDays currentDay = Selector.getInstance().selectWeekDay(viewPerson);
        System.out.println("How do you want to Choose your Hour ?");
        int hour = Selector.getInstance().selectHour();
        return new UserSetting(foodSortOption, restaurantSortOption, currentDay, hour);
    }

    public Delivery scanDeliveryInfo(ViewAdmin viewAdmin) {
        System.out.println("\tPlease enter the required information");
        System.out.print("first name: ");
        String firstname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("last name: ");
        String lastname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        System.out.println("Which salary type ? ");
        SalaryType salaryType = Selector.getInstance().selectDeliverySalaryType();
        System.out.println("How much Salary ?");
        double salary = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("Which days the delivery is available ?");
        DeliverySchedule[] schedule = Selector.getInstance().selectDeliverySchedule(viewAdmin);
        System.out.println("Which is the Delivery Vehicle ?");
        DeliveryVehicle deliveryVehicle = Selector.getInstance().selectDeliveryVehicle();

        return new Delivery(firstname, lastname, phoneNumber,
                deliveryVehicle, salaryType, salary, schedule);
    }

    public SellerMan scanSellerManInfo() {
        System.out.println("\tPlease enter the required information");
        System.out.print("userName: ");
        String username = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("password : ");
        String password = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("first name: ");
        String firstname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("last name: ");
        String lastname = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("phone number: ");
        String phoneNumber = ScannerWrapper.getInstance().nextLine().trim();
        return new SellerMan(username,password,firstname,lastname,phoneNumber);
    }

    public Food scanFoodInfo() {
        System.out.print("Food name : ");
        String foodName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Time for cooking: ");
        int foodCookTime = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return new Food(foodName, foodCookTime);
    }


    public Restaurant scanRestaurantInfo(ViewAdmin viewAdmin,Database database) {
        System.out.print("Restaurant Name : ");
        String restaurantName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Address Section\nneighbor: ");
        Address address = scanAddressInfo();
        System.out.print("Open Time : ");
        int workHoursOpen = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("Close Time : ");
        int workHoursClose = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("Which days restaurant is Open ? ");
        RestaurantSchedule[] restaurantSchedules = Selector.getInstance().selectRestaurantSchedule(viewAdmin);
        System.out.println("What is the Restaurant Type ?");
        viewAdmin.printRestaurantTypes();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantType restaurantType = RestaurantType.values()[userChoice - 1];
        System.out.println("Which foods restaurant have ? ");
        ArrayList<Item> restaurantFoods = setRestaurantFoods(viewAdmin,database);

        return new Restaurant(restaurantName, address, workHoursOpen,
                workHoursClose, restaurantSchedules, restaurantType, restaurantFoods);
    }


    public ArrayList<Item> setRestaurantFoods(ViewAdmin viewAdmin,Database database) {
        ArrayList<Item> result = new ArrayList<>();
        if (database.getFoods().size() == 0) {
            System.out.println("Food list is empty!");
            return null;
        }
        while (true) {
            viewAdmin.printFoods(database.getFoods());
            System.out.println("[" + (database.getFoods().size() + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == database.getFoods().size() + 1) {
                break;
            }
            if (result.contains(database.getFoods().get(userChoice - 1))) {
                System.out.println("Food already exist !");
            } else {
                System.out.print("Price : ");
                double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
                result.add(new Food(database.getFoods().get(userChoice - 1), price));
            }
        }
        return result;
    }

    public Department findDepartment(Database database) {
        System.out.print("Department name : ");
        String name = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Department neighbor : ");
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        for (Department department : database.getDepartments()) {
            if (department.getAddress().getNeighbor().equals(neighbor)) {
                if (department.getName().equals(name)) {
                    return department;
                }
            }
        }
        System.out.println("Cant Find Department");
        return null;
    }


    public Delivery findDepartmentDelivery(ViewAdmin viewAdmin, Department department) {
        System.out.println("Which one ?");
        viewAdmin.printDeliveries(department.getDeliveries());
        System.out.println("[" + (department.getDeliveries().size() + 1) + "]. Exit");
        int userDeliveryChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userDeliveryChoice == department.getDeliveries().size() + 1) {
            return null;
        }
        return department.getDeliveries().get(userDeliveryChoice - 1);
    }

    public Comment scanCommentFields(ViewPerson viewPerson, Customer customer, Food food,
                                     Restaurant restaurant, Delivery delivery) {
        System.out.print("Enter your message : ");
        String messege = ScannerWrapper.getInstance().nextLine().trim();
        System.out.println("How was the food rate ?");
        viewPerson.printUserRate();
        int foodRateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        UserRate foodRate = UserRate.values()[foodRateChoice - 1];
        food.addFoodRate(foodRate);
        System.out.println("How was the delivery rate ?");
        viewPerson.printUserRate();
        int deliveryRateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        UserRate deliveryRate = UserRate.values()[deliveryRateChoice - 1];
        System.out.println("How was the restaurant rate ?");
        viewPerson.printUserRate();
        int restaurantRateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        UserRate restaurantRate = UserRate.values()[restaurantRateChoice - 1];

        return new Comment(customer, food, restaurant, delivery, foodRate, deliveryRate, restaurantRate, messege);
    }

    public Supermarket scanSuperMarketInfo(ViewAdmin viewAdmin,Database database){
        System.out.print("SuperMarket Name : ");
        String superMarketName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Address Section\nneighbor: ");
        Address address = scanAddressInfo();
        System.out.print("Open Time : ");
        int workHoursOpen = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("Close Time : ");
        int workHoursClose = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("Which products supermarket has ? ");
        ArrayList<Item> superMarketProducts = setSuperMarketProducts(viewAdmin,database);

        return new Supermarket(superMarketName, address, workHoursOpen,
                workHoursClose, superMarketProducts);
    }

    public FruitMarket scanFruitMarketInfo(ViewAdmin viewAdmin, Database database){
        System.out.print("FruitMarket Name : ");
        String fruitMarketName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Address Section\nneighbor: ");
        Address address = scanAddressInfo();
        System.out.print("Open Time : ");
        int workHoursOpen = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("Close Time : ");
        int workHoursClose = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("Which products supermarket has ? ");
        ArrayList<Item> fruitMarketFruits = setFruitMarketFruits(viewAdmin,database);

        return new FruitMarket(fruitMarketName, address, workHoursOpen,
                workHoursClose, fruitMarketFruits);
    }

    public ArrayList<Item> setSuperMarketProducts(ViewAdmin viewAdmin,Database database){
        ArrayList<Item> result = new ArrayList<>();
        if (database.getProducts().size() == 0) {
            System.out.println("Product list is empty!");
            return null;
        }
        while (true) {
            viewAdmin.printProductsWithSize(database.getProducts());
            System.out.println("[" + (database.getProducts().size() + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == database.getProducts().size() + 1) {
                break;
            }
            if (result.contains(database.getProducts().get(userChoice - 1))) {
                System.out.println("Food already exist !");
            } else {
                System.out.print("Price : ");
                double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
                System.out.println("Stock : ");
                int stock = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
                result.add(new Product(database.getProducts().get(userChoice - 1), price,stock));
            }
        }
        return result;
    }

    public ArrayList<Item> setFruitMarketFruits(ViewAdmin viewAdmin,Database database){
        ArrayList<Item> result = new ArrayList<>();
        if (database.getFruits().size() == 0) {
            System.out.println("Fruit list is empty!");
            return null;
        }
        while (true) {
            viewAdmin.printFruits(database.getFruits());
            System.out.println("[" + (database.getFruits().size() + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == database.getFruits().size() + 1) {
                break;
            }
            if (result.contains(database.getFruits().get(userChoice - 1))) {
                System.out.println("Fruit already exist !");
            } else {
                System.out.print("Price : ");
                double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
                System.out.println("Stock : ");
                int stock = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
                result.add(new Fruit(database.getFruits().get(userChoice - 1), price,stock));
            }
        }
        return result;
    }
}
