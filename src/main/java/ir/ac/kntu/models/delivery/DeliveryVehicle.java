package ir.ac.kntu.models.delivery;

public enum DeliveryVehicle {
    CAR(0), BIKE(1);

    private int rate;

    DeliveryVehicle(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
