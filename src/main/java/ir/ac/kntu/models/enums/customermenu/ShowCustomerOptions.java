package ir.ac.kntu.models.enums.customermenu;

public enum ShowCustomerOptions {
    SHOW_INFO(0), SHOW_ORDERS(1), SHOW_COMMENTS(2), EXIT(3), DEFAULT(4);

    private int rate;

    ShowCustomerOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public ShowCustomerOptions findOption(int userInput) {
        ShowCustomerOptions[] options = ShowCustomerOptions.values();
        for (ShowCustomerOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
