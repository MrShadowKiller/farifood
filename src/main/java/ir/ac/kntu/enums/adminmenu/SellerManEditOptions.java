package ir.ac.kntu.enums.adminmenu;

import ir.ac.kntu.enums.sellermanmenu.SellerManMenuOptions;

public enum SellerManEditOptions {
    CHANGE_NAME(0),ADD_ITEM(1),REMOVE_ITEM(2),
    ADD_DELIVERY(3),REMOVE_DELIVERY(4),EXIT(5),
    DEFAULT(6);

    private int rate;

    SellerManEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public SellerManEditOptions findOption(int userInput) {
        SellerManEditOptions[] options = SellerManEditOptions.values();
        for (SellerManEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }

}
