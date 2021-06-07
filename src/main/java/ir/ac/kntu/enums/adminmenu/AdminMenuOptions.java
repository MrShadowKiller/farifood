package ir.ac.kntu.enums.adminmenu;

public enum AdminMenuOptions {
    ADMINS(0), CUSTOMERS(1),SELLER_MAN(2), RESTAURANTS(3),
    SUPERMARKET(4),FRUITMARKET(5), FOOD(6), ORDERS(7),
    DELIVERIES(8), EXIT(9), DEFAULT(10);

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
