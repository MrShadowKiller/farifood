package ir.ac.kntu.restaurant;

import ir.ac.kntu.WeekDays;

public enum RestaurantSchedule {
    SATURDAY(7), SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4),
    THURSDAY(5), FRIDAY(6), DEFAULT(8);

    private int rate;

    private boolean availability;

    RestaurantSchedule(int rate) {
        this.rate = rate;
        availability = false;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public RestaurantSchedule findTheSameDay(WeekDays day) {
        RestaurantSchedule[] restaurantDays = RestaurantSchedule.values();
        for (RestaurantSchedule restaurantDay : restaurantDays) {
            if (restaurantDay.getRate() == day.getRate()) {
                return restaurantDay;
            }
        }
        return null;
    }
}
