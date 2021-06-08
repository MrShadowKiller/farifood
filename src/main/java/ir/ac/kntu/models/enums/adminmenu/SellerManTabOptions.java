package ir.ac.kntu.models.enums.adminmenu;

public enum SellerManTabOptions {
    ADD_SELLER_MAN(0), REMOVE_SELLER_MAN(1), ADD_SELLER_MAN_TO_DEPARTMENT(2),
    EXIT(3), DEFAULT(4);

    private int rate;

    SellerManTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public SellerManTabOptions findOption(int userInput) {
        SellerManTabOptions[] options = SellerManTabOptions.values();
        for (SellerManTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
