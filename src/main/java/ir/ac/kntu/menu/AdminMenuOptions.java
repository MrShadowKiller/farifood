package ir.ac.kntu.menu;

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
}
