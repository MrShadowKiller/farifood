package ir.ac.kntu.models.enums.adminmenu;

public enum FruitMarketEditOptions {
    CHANGE_NAME(0), ADD_FRUIT(1), REMOVE_FRUIT(2), ADD_DELIVERY(3),
    REMOVE_DELIVERY(4), EXIT(5), DEFAULT(6);

    private int rate;

    FruitMarketEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public FruitMarketEditOptions findOption(int userInput) {
        FruitMarketEditOptions[] options = FruitMarketEditOptions.values();
        for (FruitMarketEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
