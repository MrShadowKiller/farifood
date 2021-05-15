package ir.ac.kntu.menu;
public enum StartMenuOptions {
    ADMIN_LOGIN(0),CUSTOMER_LOGIN(1),EXIT(2),DEFAULT(3);

    private int rate;

    StartMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
