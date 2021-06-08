package ir.ac.kntu.delivery;

import ir.ac.kntu.Department;
import ir.ac.kntu.order.Order;
import ir.ac.kntu.restaurant.Restaurant;
import ir.ac.kntu.user.Person;

import java.util.ArrayList;

public class Delivery extends Person {

    private DeliveryVehicle vehicleType;

    private SalaryType salaryType;

    private double salary;

    private DeliverySchedule[] schedule;

    private Department[] departments = new Department[2];

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

    public Department[] getDepartments() {
        return departments;
    }

    public boolean isFull(Department department) {
        if ((departments[0] != department && departments[1] != department) &&
                (departments[0] != null && departments[1] != null)) {
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

    public void setRestaurants(Department[] departments) {
        this.departments = departments;
    }

    public void addDepartment(Department department) {
        if (departments[0] == department || departments[1] == department) {
            return;
        }

        if (departments[0] == null) {
            departments[0] = department;
        }

        if (departments[1] == null) {
            departments[1] = department;
        }

    }

    public void removeDepartment(Department department) {
        if (departments[0] == department) {
            departments[0] = null;
        } else if (departments[1] == department) {
            departments[1] = null;
        }
        for (DeliverySchedule deliverySchedule : schedule) {
            if (deliverySchedule.getDepartment() == department) {
                deliverySchedule.setDepartment(null);
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
