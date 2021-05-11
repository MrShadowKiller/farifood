package ir.ac.kntu;

public class Delivery {
    private String fullName;

    private DeliveryVehicle vehicleType;

    private SalaryType salaryType;

    private double salary;

    private WeekDays[] schedule;


    public Delivery(String fullName, DeliveryVehicle vehicleType,
                    SalaryType salaryType, double salary,
                    WeekDays[] schedule) {

        this.fullName = fullName;
        this.vehicleType = vehicleType;
        this.salaryType = salaryType;
        this.salary = salary;
        this.schedule = schedule;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
}
