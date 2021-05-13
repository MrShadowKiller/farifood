package ir.ac.kntu;

public enum RestaurantSchedule {
    SATURDAY(0),SUNDAY(1),MONDAY(2),TUESDAY(3),WEDNESDAY(4),
    THURSDAY(5),FRIDAY(6);

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
}
