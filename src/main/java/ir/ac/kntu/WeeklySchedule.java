package ir.ac.kntu;

public enum WeeklySchedule {
    SATURDAY(0),SUNDAY(1),MONDAY(2),TUESDAY(3),WEDNESDAY(4),
    THURSDAY(5),FRIDAY(6);

    private int rate;

    private int availability;

    WeeklySchedule(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
