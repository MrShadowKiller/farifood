package ir.ac.kntu.delivery;

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
                    SalaryType salaryType, double salary,DeliverySchedule[] schedule,
                    Restaurant[] restaurants, ArrayList<Order> orders) {
        super(firstName, lastName, phoneNumber);
        this.vehicleType = vehicleType;
        this.salaryType = salaryType;
        this.salary = salary;
        this.schedule = schedule;
        this.restaurants = restaurants;
        this.orders = orders;
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

    public void setRestaurants(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void printRestaurants(){
        for (int i=0;i<restaurants.length;i++){
            System.out.println(i +". "+ restaurants[i].getName());
        }
    }

    public void printSchedule(){
        System.out.println("Weekly Schedule : ");
        for (DeliverySchedule day : schedule){
            if (day.getRestaurantIndex() != -1){
                System.out.println(day.toString() + ": " + restaurants[day.getRestaurantIndex()] + "\n");
            }
        }
    }


    public String getBriefInformation(){
        return "Full Name : " + getFirstName() + " " + getLastName() +
                "\n\tVehicle : " + vehicleType.toString();
    }
}
