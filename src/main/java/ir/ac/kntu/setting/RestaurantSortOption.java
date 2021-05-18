package ir.ac.kntu.setting;

public enum RestaurantSortOption {
    HIGH_RATE(0,"High Rate"),LOW_RATE(1,"Low Rate"),
    HIGH_COMMENTS(2,"High Comments"),LOW_COMMENTS(3,"Low Comments")
    ,RISING(4,"Rising");

    private int rate;

    private String name;

    RestaurantSortOption(int rate,String name) {
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
