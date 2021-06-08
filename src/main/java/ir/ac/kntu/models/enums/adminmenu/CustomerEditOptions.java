package ir.ac.kntu.models.enums.adminmenu;

public enum CustomerEditOptions {
    CHANGE_PERSONAL_INFO(0), CHANGE_PASSWORD(1), CHANGE_BALANCE(2),
    EXIT(3), DEFAULT(4);

    private int rate;

    CustomerEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public CustomerEditOptions findOption(int userInput) {
        CustomerEditOptions[] options = CustomerEditOptions.values();
        for (CustomerEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
