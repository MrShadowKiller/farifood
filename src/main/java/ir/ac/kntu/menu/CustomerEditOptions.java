package ir.ac.kntu.menu;

public enum CustomerEditOptions {
    CHANGE_PERSONAL_INFO(0),CHANGE_PASSWORD(1),CHANGE_BALANCE(2),
    EXIT(3),DEFAULT(4);

    private int rate;

    CustomerEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
