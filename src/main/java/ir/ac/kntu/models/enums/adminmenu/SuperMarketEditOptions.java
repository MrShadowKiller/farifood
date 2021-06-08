package ir.ac.kntu.models.enums.adminmenu;

public enum SuperMarketEditOptions {
    CHANGE_NAME(0), ADD_PRODUCT(1), REMOVE_PRODUCT(2), ADD_DELIVERY(3),
    REMOVE_DELIVERY(4), EXIT(5), DEFAULT(6);

    private int rate;

    SuperMarketEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public SuperMarketEditOptions findOption(int userInput) {
        SuperMarketEditOptions[] options = SuperMarketEditOptions.values();
        for (SuperMarketEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
