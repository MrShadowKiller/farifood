package ir.ac.kntu.menu;

public enum CustomersTabOptions {
    ADD_CUSTOMER(0),REMOVE_CUSTOMER(1),VIEW_EDIT_CUSTOMER(2),
    VIEW_CUSTOMER_ORDERS(3),EXIT(4),DEFAULT(5);

    private int rate;

    CustomersTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
