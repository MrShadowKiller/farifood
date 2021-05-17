package ir.ac.kntu.customermenu;

public enum BuyFoodTabOptions {
    SHOW_RESTAURANTS(0),SHOW_BEST_THREE(1),SHOW_WITH_BEST_FOOD(2),
    SEARCH_BY_NAME(3),SEARCH_BY_TYPE(4),EXIT(5),DEFAULT(6);

    private int rate;

    BuyFoodTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public BuyFoodTabOptions findOption(int userInput){
        BuyFoodTabOptions[] options = BuyFoodTabOptions.values();
        for (BuyFoodTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
