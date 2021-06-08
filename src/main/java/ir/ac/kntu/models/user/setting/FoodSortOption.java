package ir.ac.kntu.models.user.setting;

public enum FoodSortOption {
    HIGH_RATE(0, "High Rate"), LOW_RATE(1, "Low Rate"),
    HIGH_PRICE(2, "High Price"), LOW_PRICE(3, "Low Price");

    private int rate;

    private String name;

    FoodSortOption(int rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
