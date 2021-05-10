package ir.ac.kntu;

public class Delivery {
    private String fullName;

    private DeliveryVehicle vehicleType;

    private SalaryType salaryType;

    private double Salary;

    private WeeklySchedule[] schedule;


    public Delivery(String fullName, DeliveryVehicle vehicleType,
                    SalaryType salaryType, double salary,
                    WeeklySchedule[] schedule) {

        this.fullName = fullName;
        this.vehicleType = vehicleType;
        this.salaryType = salaryType;
        Salary = salary;
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
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public WeeklySchedule[] getSchedule() {
        return schedule;
    }

    public void setSchedule(WeeklySchedule[] schedule) {
        this.schedule = schedule;
    }
}
