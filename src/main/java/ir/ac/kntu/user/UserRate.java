package ir.ac.kntu.user;

public enum UserRate {
    VERY_BAD(1), BAD(2), AVERAGE(3), GOOD(4), GREAT(5);

    private int rate;

    UserRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}