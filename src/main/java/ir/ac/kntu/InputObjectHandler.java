package ir.ac.kntu;

import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.delivery.DeliverySchedule;
import ir.ac.kntu.delivery.DeliveryVehicle;
import ir.ac.kntu.delivery.SalaryType;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.Admin;
import ir.ac.kntu.user.CreditCard;
import ir.ac.kntu.user.Customer;
import ir.ac.kntu.user.UserSetting;

import java.time.LocalDateTime;

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
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("full address: ");
        String fullAddress = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("zip code: ");
        String zipcode = ScannerWrapper.getInstance().nextLine().trim();

        return new Admin(firstname, lastname, phoneNumber,
                username, password, new Address(neighbor, fullAddress, zipcode));
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
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("full address: ");
        String fullAddress = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("zip code: ");
        String zipcode = ScannerWrapper.getInstance().nextLine().trim();
        System.out.println("Want to add Credit Card for faster transactions ?");
        String creditCardChoice = ScannerWrapper.getInstance().nextLine().trim();

        Customer resultCustomer = new Customer(firstname, lastname, phoneNumber,
                username, password, new Address(neighbor, fullAddress, zipcode));

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

    public Delivery scanDeliveryInfo(View view){
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

        return new Delivery(firstname,lastname,phoneNumber,
                deliveryVehicle,salaryType,salary,schedule);
    }

    public SalaryType selectDeliverySalaryType(){
        SalaryType[] salaryTypes = SalaryType.values();
        System.out.println("[1]. Hourly");
        System.out.println("[2]. Per Order");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return salaryTypes[userChoice-1];
    }

    public DeliverySchedule[] selectDeliverySchedule(View view){
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

    public DeliveryVehicle selectDeliveryVehicle(){
        DeliveryVehicle[] deliveryVehicles = DeliveryVehicle.values();
        System.out.println("[1]. Car");
        System.out.println("[2]. Bike");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return deliveryVehicles[userChoice-1];
    }
}
