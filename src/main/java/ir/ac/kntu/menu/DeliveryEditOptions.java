package ir.ac.kntu.menu;

public enum DeliveryEditOptions {
    CHANGE_SALARY(0),CHANGE_VEHICLE(1),CHANGE_RESTAURANTS(2),EXIT(3),
    DEFAULT(4);

    private int rate;

    DeliveryEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
