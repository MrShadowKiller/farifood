package ir.ac.kntu;

public enum WeekDays {
    SATURDAY(0),SUNDAY(1),MONDAY(2),TUESDAY(3),WEDNESDAY(4),
    THURSDAY(5),FRIDAY(6);

    private int rate;

    WeekDays(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

}
