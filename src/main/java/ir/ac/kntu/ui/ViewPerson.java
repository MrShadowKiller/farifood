package ir.ac.kntu.ui;

import ir.ac.kntu.Department;
import ir.ac.kntu.FruitMarket;
import ir.ac.kntu.Stackable;
import ir.ac.kntu.Supermarket;
import ir.ac.kntu.delivery.Delivery;
import ir.ac.kntu.objects.Food;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.objects.StackableItem;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.restaurant.RestaurantType;
import ir.ac.kntu.setting.FoodSortOption;
import ir.ac.kntu.setting.RestaurantSortOption;
import ir.ac.kntu.user.UserRate;
import ir.ac.kntu.user.WeekDays;

import java.util.ArrayList;

public class ViewPerson {

    public void printCustomerMenu() {
        System.out.println("\tPlease use of the below options");
        System.out.println("[1].Restaurants and Foods");
        System.out.println("[2].SuperMarkets");
        System.out.println("[3].FruitMarkets");
        System.out.println("[4].Edit Information");
        System.out.println("[5].Show Information");
        System.out.println("[6].Add Balance");
        System.out.println("[7].Setting");
        System.out.println("[8].Exit");
    }

    public void printRestaurantFoodTab() {
        System.out.println("[1].Show Restaurants (By User Setting)");
        System.out.println("[2].Show Best 3 Restaurants and their Foods");
        System.out.println("[3].Show Restaurants with the best Food");
        System.out.println("[4].Search Restaurant By Name");
        System.out.println("[5].Search Restaurant By Type");
        System.out.println("[6].Search Near Restaurants");
        System.out.println("[7].Show Food Comments");
        System.out.println("[8].Exit");
    }

    public void printSellerManMenu() {
        System.out.println("[1].Edit Department Info");
        System.out.println("[2].Get orders");
        System.out.println("[3].Get Deliveries");
        System.out.println("[4].Get comments");
        System.out.println("[6].Exit");
    }

    public void printRestaurants(ArrayList<Restaurant> restaurants) {
        for (int i = 1; i <= restaurants.size(); i++) {
            System.out.println("[" + i + "]. " + getBriefInfoOfRestaurant(restaurants.get(i - 1)));
        }
        System.out.println("[" + (restaurants.size() + 1) + "]. " + "Exit");
    }

    public String getBriefInfoOfRestaurant(Restaurant restaurant) {
        return restaurant.getName() + "\t" + restaurant.getAverageRate() + " " + restaurant.getWorkHoursOpen() +
                "-" + restaurant.getWorkHoursClose();
    }

    public void printBestThreeRestaurants(ArrayList<Restaurant> restaurants) {
        for (int i = 1; i <= restaurants.size(); i++) {
            System.out.println("[" + i + "]. " + getBriefInfoOfRestaurant(restaurants.get(i - 1)));
            printBestThreeFoods(restaurants.get(i - 1));
        }
        System.out.println("[" + (restaurants.size() + 1) + "]. " + "Exit");
    }


    public void printBestThreeFoods(Restaurant restaurant) {
        restaurant.sortItemHighRating();
        for (int i = 1; i <= 3; i++) {
            System.out.println("(" + i + "). " + restaurant.getFoods().get(i - 1));
        }
    }

    public void printRestaurantSortOptions(RestaurantSortOption[] restaurantOptions) {
        for (int i = 1; i <= restaurantOptions.length; i++) {
            System.out.println("[" + i + "]. " + restaurantOptions[i - 1].getName());
        }
    }

    public void printFoodSortOptions(FoodSortOption[] foodOptions) {
        for (int i = 1; i <= foodOptions.length; i++) {
            System.out.println("[" + i + "]. " + foodOptions[i - 1].getName());
        }
    }

    public void printWeekDays() {
        WeekDays[] weekDays = WeekDays.values();
        for (int i = 1; i <= weekDays.length; i++) {
            System.out.println("[" + i + "]. " + weekDays[i - 1].toString());
        }
    }

    public void printFoodsWithoutPrice(ArrayList<Food> foods) {
        for (int i = 1; i <= foods.size(); i++) {
            System.out.println("[" + i + "]. " + foods.get(i - 1).getName() +
                    "  " + foods.get(i - 1).getTimeForCooking() + "min ");
        }
        if (foods.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printRestaurantTypes() {
        RestaurantType[] restaurantType = RestaurantType.values();
        for (int i = 1; i <= restaurantType.length; i++) {
            System.out.println("[" + i + "]. " + restaurantType[i - 1].toString());
        }
    }

    public void printDepartmentMenu() {
        System.out.println("[1].Show Information");
        System.out.println("[2].Buy Item");
        System.out.println("[3].Show Comments History");
        System.out.println("[4].CheckOut");
        System.out.println("[5].Exit");
    }

    public void printDepartmentInformation(Department department) {
        System.out.println("Name : " + department.getName());
        System.out.println("Address : " + department.getAddress());
        System.out.println("Rate : " + department.getAverageRate());
        System.out.println("Number of active deliveries : " + department.getDeliveries().size() +
                "\t WorkHours : " + department.getWorkHoursOpen() + "-" + department.getWorkHoursClose());
        if (department instanceof Restaurant) {
            System.out.print("Restaurant Type : " + ((Restaurant) department).getRestaurantType());
        }
    }

    public void printRestaurantFoodMenu(Restaurant restaurant) {
        System.out.println("\t\tName\tRate\tPrice\tTime For Cooking");
        for (int i = 1; i <= restaurant.getFoods().size(); i++) {
            System.out.println("[" + i + "]. " + restaurant.getFoods().get(i - 1).getName() + "\t" +
                    restaurant.getFoods().get(i - 1).getAverageRate() + "\t\t" + restaurant.getFoods().get(i - 1).getPrice() +
                    "$\t" + restaurant.getFoods().get(i - 1).getTimeForCooking() + "min");
        }
        System.out.println("[" + (restaurant.getFoods().size() + 1) + "]. " + "Exit");
    }

    public void printDepartmentItemMenu(Department department) {
        System.out.println("\t\tName\tRate\tPrice\tStock");
        for (int i = 1; i <= department.getItems().size(); i++) {
            System.out.println("[" + i + "]. " + department.getItems().get(i - 1).getName() + "\t" +
                    department.getItems().get(i - 1).getAverageRate() + "\t\t" + department.getItems().get(i - 1).getPrice() +
                    "$\t" + ((StackableItem) (department.getItems().get(i - 1))).getStock());
        }
        System.out.println("[" + (department.getItems().size() + 1) + "]. " + "Exit");
    }

    public void printUserRate() {
        UserRate[] userRates = UserRate.values();
        for (int i = 1; i <= userRates.length; i++) {
            System.out.println("[" + i + "]. " + userRates[i - 1].name());
        }
    }

    public void printComments(ArrayList<Comment> comments) {
        for (int i = 1; i <= comments.size(); i++) {
            System.out.println("[" + i + "]. " + comments.get(i - 1));
        }
    }

    public void printOrders(ArrayList<Order> orders) {
        for (int i = 1; i <= orders.size(); i++) {
            System.out.println("[" + i + "]. " + orders.get(i - 1).toString());
        }
        if (orders.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printDeliveries(ArrayList<Delivery> deliveries) {
        for (int i = 1; i <= deliveries.size(); i++) {
            System.out.println("[" + i + "]. " + deliveries.get(i - 1).getBriefInformation());
        }
    }

    public void printShowCustomerTab() {
        System.out.println("[1].Show Information");
        System.out.println("[2].Show Orders");
        System.out.println("[3].Show Comments");
        System.out.println("[4].Exit");
    }

    public void printAddBalanceTab() {
        System.out.println("[1].Add Credit Card");
        System.out.println("[2].Add Wallet Balance");
        System.out.println("[3].Add Credit Card Balance");
        System.out.println("[4].Activate Subscription");
        System.out.println("[5].Exit");
    }

    public void printFoodsWithPrice(ArrayList<Food> foods) {
        for (int i = 1; i <= foods.size(); i++) {
            System.out.println("[" + i + "]. " + foods.get(i - 1).getName() +
                    "  " + foods.get(i - 1).getTimeForCooking() + "min " +
                    "  " + foods.get(i - 1).getPrice() + "$");
        }
        if (foods.size() == 0) {
            System.out.println("EMPTY!");
        }
    }

    public void printSellerManEditDepartmentTab() {
        System.out.println("[1].Change Name");
        System.out.println("[2].Add Item");
        System.out.println("[3].Remove Item");
        System.out.println("[4].Add Delivery");
        System.out.println("[5].Remove Delivery");
        System.out.println("[6].Exit");
    }

    public void printOrderPeriodsSuperMarket(int workHoursOpen, int workHoursClose) {
        int counter = 1;
        for (int i = workHoursOpen; i < workHoursClose; i += 1) {
            System.out.println("[" + counter++ + "]" + i + "-" + (i + 1));
        }
    }

    public void printOrderPeriodsFruitMarket(FruitMarket fruitMarket, int workHoursOpen, int workHoursClose) {
        int counter = 1;
        for (int i = workHoursOpen; i <= workHoursClose; i += 2) {
            if (!fruitMarket.isFullPeriod(i)) {
                System.out.println("[" + counter + "]" + i + "-" + (i + 2));
            }
            counter++;
        }
    }

    public void printSuperMarkets(ArrayList<Supermarket> supermarkets) {
        for (int i = 1; i <= supermarkets.size(); i++) {
            System.out.println("[" + i + "]. " + supermarkets.get(i - 1).getName() + "  " + supermarkets.get(i - 1).getAverageRate());
        }
    }

    public void printFruitMarkets(ArrayList<FruitMarket> fruitMarkets) {
        for (int i = 1; i <= fruitMarkets.size(); i++) {
            System.out.println("[" + i + "]. " + fruitMarkets.get(i - 1).getName() + "  " + fruitMarkets.get(i - 1).getAverageRate());
        }
    }


}
