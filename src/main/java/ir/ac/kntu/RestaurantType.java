package ir.ac.kntu;

public enum RestaurantType {
    ECONOMY(0),HIGH_CLASS(1),LUXURY(2);

    private int rate;

    RestaurantType(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
