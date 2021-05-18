package ir.ac.kntu.delivery;

import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Person;

import java.util.ArrayList;

public class Delivery extends Person {

    private DeliveryVehicle vehicleType;

    private SalaryType salaryType;

    private double salary;

    private DeliverySchedule[] schedule;

    private Restaurant[] restaurants = new Restaurant[2];

    private ArrayList<Order> orders;

    public Delivery(String firstName, String lastName, String phoneNumber, DeliveryVehicle vehicleType,
                    SalaryType salaryType, double salary, DeliverySchedule[] schedule) {
        super(firstName, lastName, phoneNumber);
        this.vehicleType = vehicleType;
        this.salaryType = salaryType;
        this.salary = salary;
        this.schedule = schedule;
        orders = new ArrayList<>();
    }

    public DeliveryVehicle getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(DeliveryVehicle vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public DeliverySchedule[] getSchedule() {
        return schedule;
    }

    public void setSchedule(DeliverySchedule[] schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Restaurant[] getRestaurants() {
        return restaurants;
    }

    public boolean isFull(Restaurant restaurant) {
        if ((restaurants[0] != restaurant && restaurants[1] != restaurant) &&
                (restaurants[0] != null && restaurants[1] != null)) {
            return true;
        }
        boolean status = true;
        for (DeliverySchedule deliverySchedule : schedule) {
            if (deliverySchedule.getAvailability()) {
                status = false;
            }
        }
        return status;
    }

    public void setRestaurants(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        if (restaurants[0] == restaurant || restaurants[1] == restaurant) {
            return;
        }

        if (restaurants[0] == null) {
            restaurants[0] = restaurant;
        }

        if (restaurants[1] == null) {
            restaurants[1] = restaurant;
        }

    }

    public void removeRestaurant(Restaurant restaurant) {
        if (restaurants[0] == restaurant) {
            restaurants[0] = null;
        } else if (restaurants[1] == restaurant) {
            restaurants[1] = null;
        }
        for (DeliverySchedule deliverySchedule : schedule) {
            if (deliverySchedule.getRestaurant() == restaurant) {
                deliverySchedule.setRestaurant(null);
            }
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }


    public String getBriefInformation() {
        return "Full Name : " + getFirstName() + " " + getLastName() +
                "\n\tVehicle : " + vehicleType.toString() + "\n\tSalary : " +
                salary;
    }
}
