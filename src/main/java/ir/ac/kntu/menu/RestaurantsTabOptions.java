package ir.ac.kntu.menu;

public enum RestaurantsTabOptions {
    ADD_RESTAURANT(0),REMOVE_RESTAURANT(1),VIEW_EDIT_RESTAURANT(2),
    EXIT(3),DEFAULT(4);

    private int rate;

    RestaurantsTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
