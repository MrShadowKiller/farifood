package ir.ac.kntu.enums.customermenu;

public enum RestaurantMenuOptions {
    SHOW_INFORMATION(0), BUY_FOOD(1), SHOW_COMMENTS(2), EXIT(3), DEFAULT(4);

    private int rate;

    RestaurantMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public RestaurantMenuOptions findOption(int userInput) {
        RestaurantMenuOptions[] options = RestaurantMenuOptions.values();
        for (RestaurantMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
