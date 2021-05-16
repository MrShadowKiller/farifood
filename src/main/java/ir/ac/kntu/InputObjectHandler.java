package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantSchedule;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.CreditCard;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.UserSetting;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InputObjectHandler {
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
        String zipcode = ScannerWrapper.getInstance().nextLine().trim();

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
            System.out.print("Credit Card Number: ");
            String creditCardNumber = ScannerWrapper.getInstance().nextLine().trim();
            System.out.print("Credit Card Password: ");
            String creditCardPassword = ScannerWrapper.getInstance().nextLine().trim();
            System.out.print("Balance: ");
            double creditCardBalance = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
            resultCustomer.setCreditCard(new CreditCard(creditCardNumber, creditCardPassword, creditCardBalance));
        }
        return resultCustomer;
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

    public UserSetting scanUserSetting(View view) {
        System.out.println("How do you want the foods to be sorted ?");
        FoodSortOption foodSortOption = selectFoodSort(view);
        System.out.println("How do you want the restaurants to be sorted ?");
        RestaurantSortOption restaurantSortOption = selectRestaurantSort(view);
        System.out.println("How do you want to choose your current Day ?");
        WeekDays currentDay = selectWeekDay(view);

        return new UserSetting(foodSortOption, restaurantSortOption, currentDay);
    }

    public FoodSortOption selectFoodSort(View view) {
        FoodSortOption[] foodOptions = FoodSortOption.values();
        view.printFoodSortOptions(foodOptions);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return foodOptions[userChoice - 1];
    }

    public RestaurantSortOption selectRestaurantSort(View view) {
        RestaurantSortOption[] restaurantOptions = RestaurantSortOption.values();
        view.printRestaurantSortOptions(restaurantOptions);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return restaurantOptions[userChoice - 1];
    }

    public WeekDays selectWeekDay(View view) {
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
        view.printWeekDays();
        int dayChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return weekDays[dayChoice - 1];
    }

    public Delivery scanDeliveryInfo(View view) {
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
        DeliverySchedule[] schedule = selectDeliverySchedule(view);
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

    public DeliverySchedule[] selectDeliverySchedule(View view) {
        boolean status = true;
        DeliverySchedule[] schedule = DeliverySchedule.values();
        while (true) {
            view.printWeekDays();
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

    public Food selectFood(View view, ArrayList<Food> foods) {
        System.out.println("Which Food ?");
        view.printFoods(foods);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return foods.get(userInput - 1);
    }

    public Restaurant scanRestaurantInfo(View view,ArrayList<Food> foods) {
        System.out.print("Restaurant Name : ");
        String restaurantName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Address Section\nneighbor: ");
        Address address = scanAddressInfo();
        System.out.print("Open Time : ");
        String workHoursOpen = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Close Time : ");
        String workHoursClose = ScannerWrapper.getInstance().nextLine().trim();
        System.out.println("Which days restaurant is Open ? ");
        RestaurantSchedule[] restaurantSchedules = selectRestaurantSchedule(view);
        System.out.println("What is the Restaurant Type ?");
        view.printRestaurantTypes();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantType restaurantType = RestaurantType.values()[userChoice - 1];
        System.out.println("Which foods restaurant have ? ");
        ArrayList<Food> restaurantFoods = setRestaurantFoods(view,foods);

        return new Restaurant(restaurantName,address,workHoursOpen,
                workHoursClose,restaurantSchedules,restaurantType,restaurantFoods);
    }

    public RestaurantSchedule[] selectRestaurantSchedule(View view) {
        RestaurantSchedule[] schedule = RestaurantSchedule.values();
        while (true) {
            view.printWeekDays();
            System.out.println("[" + (schedule.length + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == schedule.length + 1) {
                break;
            }
            schedule[userChoice - 1].setAvailability(true);
        }
        return schedule;
    }

    public ArrayList<Food> setRestaurantFoods(View view,ArrayList<Food> foods){
        ArrayList<Food> result = new ArrayList<>();
        if (foods.size() == 0){
            System.out.println("Food list is empty!");
            return null;
        }
        while (true) {
            view.printFoods(foods);
            System.out.println("[" + (foods.size() + 1) + "]. Exit");
            int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
            if (userChoice == foods.size() + 1) {
                break;
            }
            if (result.contains(foods.get(userChoice-1))) {
                System.out.println("Food already exist !");
            } else {
                System.out.print("Price : ");
                double price = Double.parseDouble(ScannerWrapper.getInstance().nextLine().trim());
                result.add(new Food(foods.get(userChoice - 1), price));
            }
        }
        return result;
    }

    public Restaurant findRestaurant(ArrayList<Restaurant> restaurants){
        System.out.println("Restaurant name : ");
        String name = ScannerWrapper.getInstance().nextLine().trim();
        System.out.println("Restaurant neighbor : ");
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        for (Restaurant restaurant : restaurants){
            if (restaurant.getAddress().getNeighbor().equals(neighbor)){
                if (restaurant.getName().equals(name)){
                    return restaurant;
                }
            }
        }
        System.out.println("Cant Find Restaurant");
        return null;
    }


}
