package ir.ac.kntu.enums.sellermanmenu;

public enum SellerManMenuOptions {
    EDIT_DEPARTMENT_INFO(0),GET_ORDERS(1),GET_DELIVERIES(2),
    GET_COMMENTS(3),EXIT(4),DEFAULT(5);

    private int rate;

    SellerManMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public SellerManMenuOptions findOption(int userInput) {
        SellerManMenuOptions[] options = SellerManMenuOptions.values();
        for (SellerManMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }

}
