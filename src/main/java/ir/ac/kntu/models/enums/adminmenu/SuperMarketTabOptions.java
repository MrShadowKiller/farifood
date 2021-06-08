package ir.ac.kntu.models.enums.adminmenu;

public enum SuperMarketTabOptions {
    ADD_SUPERMARKET(0), REMOVE_SUPERMARKET(1), VIEW_EDIT_SUPERMARKET(2),
    VIEW_ORDERS(3), VIEW_PRODUCTS(4), VIEW_COMMENTS(5), VIEW_DELIVERIES(6),
    EXIT(7), DEFAULT(8);

    private int rate;

    SuperMarketTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public SuperMarketTabOptions findOption(int userInput) {
        SuperMarketTabOptions[] options = SuperMarketTabOptions.values();
        for (SuperMarketTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
