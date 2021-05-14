package ir.ac.kntu.delivery;

public enum SalaryType {
    HOURLY(0),DAILY(1);

    private int rate;

    SalaryType(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
