package ir.ac.kntu.adminmenu;

public enum RestaurantEditOptions {
    CHANGE_NAME(0),CHANGE_WORK_HOURS(1),
    CHANGE_SCHEDULE(2),ADD_FOOD(3),REMOVE_FOOD(4),ADD_DELIVERY(5),
    REMOVE_DELIVERY(6),EXIT(7),DEFAULT(8);

    private int rate;

    RestaurantEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public RestaurantEditOptions findOption(int userInput){
        RestaurantEditOptions[] options = RestaurantEditOptions.values();
        for (RestaurantEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
