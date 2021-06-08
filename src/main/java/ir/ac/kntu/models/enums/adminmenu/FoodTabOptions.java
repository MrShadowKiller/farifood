package ir.ac.kntu.models.enums.adminmenu;

public enum FoodTabOptions {
    ADD_FOOD(0), REMOVE_FOOD(1), VIEW_FOODS(2), VIEW_FOOD_COMMENTS(3),
    EXIT(4), DEFAULT(5);

    private int rate;

    FoodTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public FoodTabOptions findOption(int userInput) {
        FoodTabOptions[] options = FoodTabOptions.values();
        for (FoodTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
