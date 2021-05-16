package ir.ac.kntu.menu;

public enum FoodTabOptions {
    ADD_FOOD(0),REMOVE_FOOD(1),VIEW_FOODS(2),VIEW_FOOD_COMMENTS(3),
    EXIT(4),DEFAULT(5);

    private int rate;

    FoodTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
