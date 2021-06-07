package ir.ac.kntu.enums.adminmenu;

public enum AdminMenuOptions {
    ADMINS(0), CUSTOMERS(1), RESTAURANTS(2),SUPERMARKET(3),FRUITMARKET(4), FOOD(5), ORDERS(6),
    DELIVERIES(7), EXIT(8), DEFAULT(9);

    private int rate;

    AdminMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public AdminMenuOptions findOption(int userInput) {
        AdminMenuOptions[] options = AdminMenuOptions.values();
        for (AdminMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
