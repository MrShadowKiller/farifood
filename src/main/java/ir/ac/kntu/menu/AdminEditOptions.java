package ir.ac.kntu.menu;

public enum AdminEditOptions {
    CHANGE_PERSONAL_INFO(0),CHANGE_PASSWORD(1),CHANGE_BALANCE(2),
    EXIT(3),DEFAULT(4);

    private int rate;

    AdminEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
