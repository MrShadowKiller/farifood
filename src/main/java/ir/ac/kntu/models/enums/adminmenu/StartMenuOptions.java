package ir.ac.kntu.models.enums.adminmenu;

public enum StartMenuOptions {
    ADMIN_LOGIN(0), CUSTOMER_LOGIN(1), SELLERMAN_LOGIN(2), EXIT(3), DEFAULT(4);

    private int rate;

    StartMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public StartMenuOptions findOption(int userInput) {
        StartMenuOptions[] options = StartMenuOptions.values();
        for (StartMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
