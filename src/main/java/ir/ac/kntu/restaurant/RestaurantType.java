package ir.ac.kntu.restaurant;

public enum RestaurantType {
    ECONOMY(0), HIGH_CLASS(1), LUXURY(2), DEFAULT(3);

    private int rate;

    RestaurantType(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public RestaurantType findOption(int userInput) {
        RestaurantType[] options = RestaurantType.values();
        for (RestaurantType option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
