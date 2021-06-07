package ir.ac.kntu.enums.adminmenu;

public enum AdminMenuOptions {
    ADMINS(0), CUSTOMERS(1), RESTAURANTS(2),SUPERMARKET(3), FOOD(4), ORDERS(5),
    DELIVERIES(6), EXIT(7), DEFAULT(8);

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
