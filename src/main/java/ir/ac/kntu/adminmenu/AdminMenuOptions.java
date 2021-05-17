package ir.ac.kntu.adminmenu;

public enum AdminMenuOptions {
    ADMINS(0),CUSTOMERS(1),RESTAURANTS(2),FOOD(3),ORDERS(4),
    DELIVERIES(5),EXIT(6),DEFAULT(7);

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

    public AdminMenuOptions findOption(int userInput){
        AdminMenuOptions[] options = AdminMenuOptions.values();
        for (AdminMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
