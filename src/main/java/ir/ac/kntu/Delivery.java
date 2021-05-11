package ir.ac.kntu;

import java.util.ArrayList;

public class Delivery extends Person{

    private DeliveryVehicle vehicleType;

    private SalaryType salaryType;

    private double salary;

    private WeekDays[] schedule;

    private ArrayList<Order> orders;

    public Delivery(String firstName, String lastName, String phoneNumber, DeliveryVehicle vehicleType,
                    SalaryType salaryType, double salary, WeekDays[] schedule) {
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

    public WeekDays[] getSchedule() {
        return schedule;
    }

    public void setSchedule(WeekDays[] schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public String getBriefInformation(){
        return "Full Name : " + getFirstName() + " " + getLastName() +
                "\n\tVehicle : " + vehicleType.toString();
    }
}
