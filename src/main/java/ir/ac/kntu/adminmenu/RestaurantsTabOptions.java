package ir.ac.kntu.adminmenu;

public enum RestaurantsTabOptions {
    ADD_RESTAURANT(0), REMOVE_RESTAURANT(1), VIEW_EDIT_RESTAURANT(2),
    VIEW_ORDERS(3), VIEW_FOODS(4), VIEW_COMMENTS(5), VIEW_DELIVERIES(6),
    EXIT(7), DEFAULT(8);

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

    public RestaurantsTabOptions findOption(int userInput) {
        RestaurantsTabOptions[] options = RestaurantsTabOptions.values();
        for (RestaurantsTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
