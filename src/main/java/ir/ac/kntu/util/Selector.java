package ir.ac.kntu.util;

import ir.ac.kntu.Database;
import ir.ac.kntu.models.departments.Department;
import ir.ac.kntu.models.departments.FruitMarket;
import ir.ac.kntu.models.departments.Supermarket;
import ir.ac.kntu.models.delivery.Delivery;
import ir.ac.kntu.models.delivery.DeliverySchedule;
import ir.ac.kntu.models.delivery.DeliveryVehicle;
import ir.ac.kntu.models.delivery.SalaryType;
import ir.ac.kntu.models.departments.restaurant.Restaurant;
import ir.ac.kntu.models.departments.restaurant.RestaurantSchedule;
import ir.ac.kntu.models.departments.restaurant.RestaurantType;
import ir.ac.kntu.models.objects.Food;
import ir.ac.kntu.models.objects.Fruit;
import ir.ac.kntu.models.objects.Item;
import ir.ac.kntu.models.objects.Product;
import ir.ac.kntu.models.user.setting.FoodSortOption;
import ir.ac.kntu.models.user.setting.RestaurantSortOption;
import ir.ac.kntu.ui.ViewAdmin;
import ir.ac.kntu.ui.ViewPerson;
import ir.ac.kntu.models.user.Admin;
import ir.ac.kntu.models.user.Customer;
import ir.ac.kntu.models.user.SellerMan;
import ir.ac.kntu.models.user.WeekDays;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Selector {
    private static final Selector INSTANCE = new Selector();

    private Selector() {
    }

    public static Selector getInstance() {
        return INSTANCE;
    }

    public Restaurant selectDefaultRestaurantCustomer(ArrayList<Restaurant> restaurants, Customer customer, ViewPerson viewPerson) {
        ArrayList<Restaurant> openRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.isOpen(customer.getUserSetting())) {
                openRestaurants.add(restaurant);
            }
        }
        viewPerson.printRestaurants(openRestaurants);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == openRestaurants.size() + 1) {
            return null;
        } else {
            return openRestaurants.get(userChoice - 1);
        }
    }


    public Restaurant selectBestThreeRestaurant(ArrayList<Restaurant> restaurants, Customer customer, ViewPerson viewPerson) {
        ArrayList<Restaurant> bestThreeRestaurants = new ArrayList<>(3);
        for (Restaurant restaurant : restaurants) {
            if (restaurant.isOpen(customer.getUserSetting())) {
                bestThreeRestaurants.add(restaurant);
            }
            if (bestThreeRestaurants.size() == 3) {
                break;
            }
        }
        viewPerson.printBestThreeRestaurants(bestThreeRestaurants);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == bestThreeRestaurants.size() + 1) {
            return null;
        } else {
            return bestThreeRestaurants.get(userChoice - 1);
        }
    }

    public Restaurant selectBestRestaurantForFood(ArrayList<Restaurant> restaurants, ArrayList<Food> foods,
                                                  ViewPerson viewPerson, Customer customer) {
        System.out.println("For which food ?");
        viewPerson.printFoodsWithoutPrice(foods);
        System.out.println("[" + (foods.size() + 1) + "]. " + "Exit");
        int foodChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (foodChoice == foods.size() + 1) {
            return null;
        }
        ArrayList<Restaurant> selectedRestaurant = findRestaurantWithFood(restaurants, foods.get(foodChoice - 1), customer);
        System.out.println("Choose one :");
        viewPerson.printRestaurants(selectedRestaurant);
        int restaurantChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (restaurantChoice == selectedRestaurant.size() + 1) {
            return null;
        }
        return selectedRestaurant.get(restaurantChoice - 1);
    }

    public ArrayList<Restaurant> findRestaurantWithFood(ArrayList<Restaurant> restaurants, Food food, Customer customer) {
        ArrayList<Restaurant> result = new ArrayList<>();
        for (int i = 1; i <= restaurants.size(); i++) {
            if (restaurants.get(i - 1).getFoods().contains(food) && restaurants.get(i - 1).isOpen(customer.getUserSetting())) {
                result.add(restaurants.get(i - 1));
            }
            if (result.size() == 5) {
                break;
            }
        }

        return result;
    }

    public Restaurant findRestaurantByName(ArrayList<Restaurant> restaurants, Customer customer) {
        System.out.print("Restaurant Name: ");
        String restaurantName = ScannerWrapper.getInstance().nextLine().trim();
        System.out.print("Restaurant Neighbor");
        String neighbor = ScannerWrapper.getInstance().nextLine().trim();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName) &&
                    restaurant.getAddress().getNeighbor().equals(neighbor)) {
                if (restaurant.isOpen(customer.getUserSetting())) {
                    return restaurant;
                }
            }
        }
        System.out.println("Didnt Found!");
        return null;
    }

    public Restaurant selectRestaurantByType(ArrayList<Restaurant> restaurants, Customer customer, ViewPerson viewPerson) {
        RestaurantType restaurantType = selectRestaurantType(viewPerson);
        ArrayList<Restaurant> sameRestaurantTypes = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantType() == restaurantType && restaurant.isOpen(customer.getUserSetting())) {
                sameRestaurantTypes.add(restaurant);
            }
        }
        if (sameRestaurantTypes.size() == 0) {
            System.out.println("Not Found!");
            return null;
        }
        System.out.println("Select One :");
        viewPerson.printRestaurants(sameRestaurantTypes);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return restaurants.get(userChoice - 1);
    }

    public RestaurantType selectRestaurantType(ViewPerson viewPerson) {
        System.out.println("Which restaurant Type ?");
        viewPerson.printRestaurantTypes();
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        RestaurantType restaurantType = RestaurantType.DEFAULT;

        return (restaurantType.findOption(userChoice - 1));
    }

    public Restaurant selectNearRestaurant(ArrayList<Restaurant> restaurants, Customer customer, ViewPerson viewPerson) {
        ArrayList<Restaurant> nearRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getAddress().getNeighbor().equals(customer.getAddress().getNeighbor())) {
                nearRestaurants.add(restaurant);
            }
        }
        viewPerson.printRestaurants(nearRestaurants);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == nearRestaurants.size() + 1) {
            return null;
        }
        return nearRestaurants.get(userChoice - 1);
    }


    public FoodSortOption selectFoodSort(ViewPerson viewPerson) {
        FoodSortOption[] foodOptions = FoodSortOption.values();
        viewPerson.printFoodSortOptions(foodOptions);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return foodOptions[userChoice - 1];
    }

    public RestaurantSortOption selectRestaurantSort(ViewPerson viewPerson) {
        RestaurantSortOption[] restaurantOptions = RestaurantSortOption.values();
        viewPerson.printRestaurantSortOptions(restaurantOptions);
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return restaurantOptions[userChoice - 1];
    }

    public WeekDays selectWeekDay(ViewPerson viewPerson) {
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
        viewPerson.printWeekDays();
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


    public Delivery selectRestaurantDelivery(ViewAdmin viewAdmin, Restaurant restaurant, Database database) {
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

                database.getDeliveries().get(userDeliveryChoice - 1).addDepartment(restaurant);
                database.getDeliveries().get(userDeliveryChoice - 1).getSchedule()[userRestScheChoice - 1].setDepartment(restaurant);
                return database.getDeliveries().get(userDeliveryChoice - 1);
            } else {
                System.out.println("No empty schedule for delivery or restaurant!");
            }
        }
        return null;
    }

    public Delivery selectFreeDelivery(Database database, Department department) {
        for (Delivery delivery : database.getDeliveries()) {
            if (!delivery.isFull(department)) {
                return delivery;
            }
        }
        return null;
    }


    public Delivery selectToRemoveDelivery(ViewAdmin viewAdmin, Database database) {
        System.out.println("Choose one of the deliveries : ");
        viewAdmin.printDeliveries(database.getDeliveries());
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return database.getDeliveries().get(userInput - 1);
    }

    public SellerMan selectSellerMan(ViewAdmin viewAdmin, Database database) {
        System.out.println("Choose one of the SellerMen : ");
        viewAdmin.printSellerMen(database.getSellerMen());
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return database.getSellerMen().get(userInput - 1);
    }

    public void selectRestaurantWorkHours(Restaurant restaurant) {
        System.out.println("Work Opens at : ");
        restaurant.setWorkHoursOpen(Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim()));
        System.out.println("Work Closes at : ");
        restaurant.setWorkHoursClose(Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim()));
    }

    public Customer selectCustomerHandler(ViewAdmin viewAdmin, Admin admin, Database database) {
        viewAdmin.printCustomers(database.getCustomers());
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getCustomers().size() + 1) {
            return null;
        } else {
            return database.getCustomers().get(userChoice - 1);
        }
    }

    public Restaurant selectRestaurantHandler(Admin admin, ViewAdmin viewAdmin, Database database) {
        viewAdmin.printRestaurants(database.getRestaurants());
        System.out.println("[" + (database.getRestaurants().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getRestaurants().size() + 1) {
            return null;
        } else {
            return database.getRestaurants().get(userChoice - 1);
        }
    }

    public Department selectDepartmentHandler(Admin admin, ViewAdmin viewAdmin, Database database) {
        viewAdmin.printDepartments(database.getDepartments());
        System.out.println("[" + (database.getDepartments().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getDepartments().size() + 1) {
            return null;
        } else {
            return database.getDepartments().get(userChoice - 1);
        }
    }

    public Supermarket selectSuperMarketHandler(ViewAdmin viewAdmin, Database database) {
        viewAdmin.printSuperMarkets(database.getSuperMarkets());
        System.out.println("[" + (database.getSuperMarkets().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getSuperMarkets().size() + 1) {
            return null;
        } else {
            return database.getSuperMarkets().get(userChoice - 1);
        }
    }

    public Supermarket selectSuperMarketHandler(ViewPerson viewPerson, Database database) {
        viewPerson.printSuperMarkets(database.getSuperMarkets());
        System.out.println("[" + (database.getSuperMarkets().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getSuperMarkets().size() + 1) {
            return null;
        } else {
            return database.getSuperMarkets().get(userChoice - 1);
        }
    }

    public FruitMarket selectFruitMarketHandler(ViewAdmin viewAdmin, Database database) {
        viewAdmin.printFruitMarkets(database.getFruitMarkets());
        System.out.println("[" + (database.getFruitMarkets().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getFruitMarkets().size() + 1) {
            return null;
        } else {
            return database.getFruitMarkets().get(userChoice - 1);
        }
    }

    public FruitMarket selectFruitMarketHandler(ViewPerson viewPerson, Database database) {
        viewPerson.printFruitMarkets(database.getFruitMarkets());
        System.out.println("[" + (database.getFruitMarkets().size() + 1) + "]. " + "Exit");
        int userChoice = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        if (userChoice == database.getFruitMarkets().size() + 1) {
            return null;
        } else {
            return database.getFruitMarkets().get(userChoice - 1);
        }
    }


    public Food selectFood(ViewAdmin viewAdmin, ArrayList<Food> foods) {
        if (foods.size() == 0) {
            System.out.println("NO FOOD AVAILABLE");
            return null;
        }
        System.out.println("Which Food ?");
        viewAdmin.printFoods(foods);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return foods.get(userInput - 1);
    }

    public Product selectProduct(ViewAdmin viewAdmin, ArrayList<Product> products) {
        if (products.size() == 0) {
            System.out.println("NO Product AVAILABLE");
            return null;
        }
        System.out.println("Which Product ?");
        viewAdmin.printProductsWithSize(products);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return products.get(userInput - 1);
    }

    public Fruit selectFruit(ViewAdmin viewAdmin, ArrayList<Fruit> fruits) {
        if (fruits.size() == 0) {
            System.out.println("NO Fruit AVAILABLE");
            return null;
        }
        System.out.println("Which fruit ?");
        viewAdmin.printFruits(fruits);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return fruits.get(userInput - 1);
    }

    public Item selectItem(ViewAdmin viewAdmin, ArrayList<Item> items) {
        if (items.size() == 0) {
            System.out.println("NO Items AVAILABLE");
            return null;
        }
        System.out.println("Which Item ?");
        viewAdmin.printItems(items);
        int userInput = Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim());
        return items.get(userInput - 1);
    }

    public int orderPeriodSuperMarketSelector(Supermarket supermarket) {
        ViewPerson viewPerson = new ViewPerson();
        viewPerson.printOrderPeriodsSuperMarket(supermarket.getWorkHoursOpen(), supermarket.getWorkHoursClose());
        return Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim()) + supermarket.getWorkHoursOpen() - 1;
    }

    public int orderPeriodFruitMarketSelector(FruitMarket fruitMarket) {
        ViewPerson viewPerson = new ViewPerson();
        viewPerson.printOrderPeriodsFruitMarket(fruitMarket, fruitMarket.getWorkHoursOpen(), fruitMarket.getWorkHoursClose());
        return 2 * Integer.parseInt(ScannerWrapper.getInstance().nextLine().trim()) + fruitMarket.getWorkHoursOpen() - 2;
    }


}
