package ir.ac.kntu.delivery;

import ir.ac.kntu.Department;
import ir.ac.kntu.restaurant.Restaurant;

public enum DeliverySchedule {
    SATURDAY(7), SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4),
    THURSDAY(5), FRIDAY(6), DEFAULT(8);

    private int rate;

    private Department department;

    private boolean availability;

    DeliverySchedule(int rate) {
        this.rate = rate;
        availability = false;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentIndex) {
        this.department = departmentIndex;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
