package ir.ac.kntu.delivery;

public enum DeliverySchedule {
    SATURDAY(0),SUNDAY(1),MONDAY(2),TUESDAY(3),WEDNESDAY(4),
    THURSDAY(5),FRIDAY(6);

    private int rate;

    private int restaurantIndex;

    DeliverySchedule(int rate) {
        this.rate = rate;
        restaurantIndex = -1;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRestaurantIndex() {
        return restaurantIndex;
    }

    public void setRestaurantIndex(int restaurantIndex) {
        this.restaurantIndex= restaurantIndex;
    }
}
