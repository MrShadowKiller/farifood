package ir.ac.kntu.enums.adminmenu;

public enum FruitMarketTabOptions {
    ADD_FRUITMARKET(0), REMOVE_FRUITMARKET(1), VIEW_EDIT_FRUITMARKET(2),
    VIEW_ORDERS(3), VIEW_FRUITS(4), VIEW_COMMENTS(5), VIEW_DELIVERIES(6),
    EXIT(7), DEFAULT(8);

    private int rate;

    FruitMarketTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public FruitMarketTabOptions findOption(int userInput) {
        FruitMarketTabOptions[] options = FruitMarketTabOptions.values();
        for (FruitMarketTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
