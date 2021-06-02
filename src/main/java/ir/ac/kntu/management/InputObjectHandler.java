package ir.ac.kntu.management;

import ir.ac.kntu.Database;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.setting.UserSetting;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewCustomer;
import ir.ac.kntu.user.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InputObjectHandler {
    private Database database;

    public InputObjectHandler(Database database){
        this.database = database;
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

    public UserSetting scanUserSetting(ViewCustomer viewCustomer) {
        System.out.println("How do you want the foods to be sorted ?");
        FoodSortOption foodSortOption = selectFoodSort(viewCustomer);
        System.out.println("How do you want the restaurants to be sorted ?");
        RestaurantSortOption restaurantSortOption = selectRestaurantSort(viewCustomer);
        System.out.println("How do you want to choose your current Day ?");
        WeekDays currentDay = selectWeekDay(viewCustomer);
        System.out.println("How do you want to Choose your Hour ?");
        int hour = selectHour();
        return new UserSetting(foodSortOption, restaurantSortOption, currentDay, hour);
    }

    public FoodSortOption selectFoodSort(ViewCustomer viewCustomer) {
        FoodSortOption[] foodOptions = FoodSortOption.values();
        viewCustomer.printFoodSortOptions(foodOptions);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return foodOptions[userChoice - 1];
    }

    public RestaurantSortOption selectRestaurantSort(ViewCustomer viewCustomer) {
        RestaurantSortOption[] restaurantOptions = RestaurantSortOption.values();
        viewCustomer.printRestaurantSortOptions(restaurantOptions);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return restaurantOptions[userChoice - 1];
    }

    public WeekDays selectWeekDay(ViewCustomer viewCustomer) {
        WeekDays[] weekDays = WeekDays.values();
        System.out.println("1.Manual");
        System.out.println("2.Automatic (By System Date)");
        int dateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());

        if (dateChoice == 2) {
            for (WeekDays weekDay : weekDays) {
                if (weekDay.getRate() == LocalDateTime.now().getDayOfWeek().getValue()) {
                    return weekDay;
                }
            }
        }
        System.out.println("Which day do you want to choose ? ");
        viewCustomer.printWeekDays();
        int dayChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return weekDays[dayChoice - 1];
    }


    public int selectHour() {
        System.out.println("1.Manual");
        System.out.println("2.Automatic (By System Time)");
        int timeTypeChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());

        if (timeTypeChoice == 2) {
            return LocalDateTime.now().getHour();
        } else {
            System.out.print("Enter hour (0-24) :");
            return Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        }
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
        SalaryType salaryType = selectDeliverySalaryType();
        System.out.println("How much Salary ?");
        double salary = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("Which days the delivery is available ?");
        DeliverySchedule[] schedule = selectDeliverySchedule(viewAdmin);
        System.out.println("Which is the Delivery Vehicle ?");
        DeliveryVehicle deliveryVehicle = selectDeliveryVehicle();

        return new Delivery(firstname, lastname, phoneNumber,
                deliveryVehicle, salaryType, salary, schedule);
    }

    public SalaryType selectDeliverySalaryType() {
        SalaryType[] salaryTypes = SalaryType.values();
        System.out.println("[1]. Hourly");
        System.out.println("[2]. Per Order");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return salaryTypes[userChoice - 1];
    }

    public DeliverySchedule[] selectDeliverySchedule(ViewAdmin viewAdmin) {
        boolean status = true;
        DeliverySchedule[] schedule = DeliverySchedule.values();
        while (true) {
            viewAdmin.printWeekDays();
            System.out.println("[" + (schedule.length + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == schedule.length + 1) {
                break;
            }
            schedule[userChoice - 1].setAvailability(true);
        }
        return schedule;
    }

    public DeliveryVehicle selectDeliveryVehicle() {
        DeliveryVehicle[] deliveryVehicles = DeliveryVehicle.values();
        System.out.println("[1]. Car");
        System.out.println("[2]. Bike");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return deliveryVehicles[userChoice - 1];
    }

    public Food scanFoodInfo() {
        System.out.print("Food name : ");
        String foodName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Time for cooking: ");
        int foodCookTime = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return new Food(foodName, foodCookTime);
    }

    public Food selectFood(ViewAdmin viewAdmin) {
        if (database.getFoods().size() == 0) {
            System.out.println("NO FOOD AVAILABLE");
            return null;
        }
        System.out.println("Which Food ?");
        viewAdmin.printFoods(database.getFoods());
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return database.getFoods().get(userInput - 1);
    }

    public Restaurant scanRestaurantInfo(ViewAdmin viewAdmin) {
        System.out.print("Restaurant Name : ");
        String restaurantName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Address Section\nneighbor: ");
        Address address = scanAddressInfo();
        System.out.print("Open Time : ");
        int workHoursOpen = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.print("Close Time : ");
        int workHoursClose = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        System.out.println("Which days restaurant is Open ? ");
        RestaurantSchedule[] restaurantSchedules = selectRestaurantSchedule(viewAdmin);
        System.out.println("What is the Restaurant Type ?");
        viewAdmin.printRestaurantTypes();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantType restaurantType = RestaurantType.values()[userChoice - 1];
        System.out.println("Which foods restaurant have ? ");
        ArrayList<Food> restaurantFoods = setRestaurantFoods(viewAdmin);

        return new Restaurant(restaurantName, address, workHoursOpen,
                workHoursClose, restaurantSchedules, restaurantType, restaurantFoods);
    }

    public RestaurantSchedule[] selectRestaurantSchedule(ViewAdmin viewAdmin) {
        RestaurantSchedule[] schedule = RestaurantSchedule.values();
        while (true) {
            viewAdmin.printWeekDays();
            System.out.println("[" + (schedule.length + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == schedule.length + 1) {
                break;
            }
            schedule[userChoice - 1].setAvailability(true);
        }
        return schedule;
    }

    public ArrayList<Food> setRestaurantFoods(ViewAdmin viewAdmin) {
        ArrayList<Food> result = new ArrayList<>();
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

    public Restaurant findRestaurant() {
        System.out.print("Restaurant name : ");
        String name = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Restaurant neighbor : ");
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        for (Restaurant restaurant : database.getRestaurants()) {
            if (restaurant.getAddress().getNeighbor().equals(neighbor)) {
                if (restaurant.getName().equals(name)) {
                    return restaurant;
                }
            }
        }
        System.out.println("Cant Find Restaurant");
        return null;
    }

    public Delivery selectRestaurantDelivery(ViewAdmin viewAdmin,Restaurant restaurant) {
        System.out.println("Which Delivery ?");
        viewAdmin.printDeliveries(database.getDeliveries());
        int userDeliveryChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (database.getDeliveries().get(userDeliveryChoice - 1).isFull(restaurant)) {
            System.out.println("Delivery is full!");
            return null;

        }

        while (true) {
            System.out.println("Which day delivery will work ? ");
            viewAdmin.printRestaurantSchedule(restaurant);
            System.out.println("[" + (restaurant.getSchedule().length + 1) + "]. Exit");

            int userRestScheChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());

            if (userRestScheChoice == restaurant.getSchedule().length + 1) {
                break;
            }

            if (database.getDeliveries().get(userDeliveryChoice - 1).getSchedule()[userRestScheChoice - 1].getAvailability()
                    && restaurant.getSchedule()[userRestScheChoice - 1].getAvailability()) {

                database.getDeliveries().get(userDeliveryChoice - 1).addRestaurant(restaurant);
                database.getDeliveries().get(userDeliveryChoice - 1).getSchedule()[userRestScheChoice - 1].setRestaurant(restaurant);
                return database.getDeliveries().get(userDeliveryChoice - 1);
            } else {
                System.out.println("No empty schedule for delivery or restaurant!");
            }
        }
        return null;
    }

    public Delivery findRestaurantDelivery(ViewAdmin viewAdmin, Restaurant restaurant) {
        System.out.println("Which one ?");
        viewAdmin.printDeliveries(restaurant.getDeliveries());
        System.out.println("[" + (restaurant.getDeliveries().size() + 1) + "]. Exit");
        int userDeliveryChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userDeliveryChoice == restaurant.getDeliveries().size() + 1) {
            return null;
        }
        return restaurant.getDeliveries().get(userDeliveryChoice - 1);
    }

    public Comment scanCommentFields(ViewCustomer viewCustomer, Customer customer, Food food,
                                     Restaurant restaurant, Delivery delivery) {
        System.out.print("Enter your message : ");
        String messege = ScannerWrapper.getInstance().nextLine().trim();
        System.out.println("How was the food rate ?");
        viewCustomer.printUserRate();
        int foodRateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        UserRate foodRate = UserRate.values()[foodRateChoice - 1];
        food.addFoodRate(foodRate);
        System.out.println("How was the delivery rate ?");
        viewCustomer.printUserRate();
        int deliveryRateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        UserRate deliveryRate = UserRate.values()[deliveryRateChoice - 1];
        System.out.println("How was the restaurant rate ?");
        viewCustomer.printUserRate();
        int restaurantRateChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        UserRate restaurantRate = UserRate.values()[restaurantRateChoice - 1];

        return new Comment(customer, food, restaurant, delivery, foodRate, deliveryRate, restaurantRate, messege);
    }

    public Delivery selectToRemoveDelivery(ViewAdmin viewAdmin){
        System.out.println("Choose one of the deliveries : ");
        viewAdmin.printDeliveries(database.getDeliveries());
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return database.getDeliveries().get(userInput-1);
    }

    public void selectRestaurantWorkHours(Restaurant restaurant){
        System.out.println("Work Opens at : ");
        restaurant.setWorkHoursOpen(Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim()));
        System.out.println("Work Closes at : ");
        restaurant.setWorkHoursClose(Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim()));
    }

    public Customer selectCustomerHandler(ViewAdmin viewAdmin,Admin admin) {
        viewAdmin.printCustomers(database.getCustomers());
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getCustomers().size() + 1) {
            return null;
        } else {
            return database.getCustomers().get(userChoice - 1);
        }
    }

    public Restaurant selectRestaurantHandler(Admin admin,ViewAdmin viewAdmin) {
        viewAdmin.printRestaurants(database.getRestaurants());
        System.out.println("[" + (database.getRestaurants().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getRestaurants().size() + 1) {
            return null;
        } else {
            return database.getRestaurants().get(userChoice - 1);
        }
    }
}
