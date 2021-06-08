package ir.ac.kntu.models.enums.customermenu;

public enum CustomerMenuOptions {
    RESTAURANTS_FOODS(0), SUPERMARKETS(1), FRUITMARKETS(2), EDIT_INFORMATION(3),
    SHOW_INFORMATION(4), ADD_BALANCE(5), SETTING(6), EXIT(7), DEFAULT(8);

    private int rate;

    CustomerMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public CustomerMenuOptions findOption(int userInput) {
        CustomerMenuOptions[] options = CustomerMenuOptions.values();
        for (CustomerMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
