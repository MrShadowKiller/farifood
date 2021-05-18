package ir.ac.kntu.adminmenu;

public enum AdminEditOptions {
    CHANGE_PERSONAL_INFO(0), CHANGE_PASSWORD(1), CHANGE_BALANCE(2),
    EXIT(3), DEFAULT(4);

    private int rate;

    AdminEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public AdminEditOptions findOption(int userInput) {
        AdminEditOptions[] options = AdminEditOptions.values();
        for (AdminEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
