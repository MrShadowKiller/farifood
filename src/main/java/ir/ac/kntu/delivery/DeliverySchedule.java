package ir.ac.kntu.delivery;

import ir.ac.kntu.restaurant.Restaurant;

public enum DeliverySchedule {
    SATURDAY(7),SUNDAY(1),MONDAY(2),TUESDAY(3),WEDNESDAY(4),
    THURSDAY(5),FRIDAY(6);

    private int rate;

    private Restaurant restaurant;

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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurantIndex) {
        this.restaurant= restaurantIndex;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
