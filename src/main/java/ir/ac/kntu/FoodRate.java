package ir.ac.kntu;

public enum FoodRate {
    VERY_BAD(1), BAD(2), AVERAGE(3), GOOD(4), GREAT(5);

    private int rate;

    FoodRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
