package ir.ac.kntu.customermenu;

import ir.ac.kntu.adminmenu.AdminEditOptions;

public enum CustomerMenuOptions {
    BUY_FOOD(0),EDIT_INFORMATION(1),SHOW_INFORMATION(2),ADD_BALANCE(3),
    SETTING(4),EXIT(5),DEFAULT(6);

    private int rate;

    CustomerMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public CustomerMenuOptions findOption(int userInput){
        CustomerMenuOptions[] options = CustomerMenuOptions.values();
        for (CustomerMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}