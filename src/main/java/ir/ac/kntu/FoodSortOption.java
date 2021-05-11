package ir.ac.kntu;

public enum FoodSortOption {
    HIGH_RATE(0),LOW_RATE(1),HIGH_PRICE(2),LOW_PRICE(3);

    private int rate;

    FoodSortOption(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
