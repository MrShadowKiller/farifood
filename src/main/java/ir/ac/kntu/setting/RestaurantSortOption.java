package ir.ac.kntu.setting;

public enum RestaurantSortOption {
    HIGH_RATE(0),LOW_RATE(1),HIGH_COMMENT(2),LOW_COMMENT(3),RISING(4);

    private int rate;

    RestaurantSortOption(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
