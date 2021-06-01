package ir.ac.kntu.enums.adminmenu;

public enum CustomersTabOptions {
    ADD_CUSTOMER(0), REMOVE_CUSTOMER(1), VIEW_EDIT_CUSTOMER(2),
    VIEW_CUSTOMER_ORDERS(3), VIEW_COMMENTS(4), EXIT(5), DEFAULT(6);

    private int rate;

    CustomersTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public CustomersTabOptions findOption(int userInput) {
        CustomersTabOptions[] options = CustomersTabOptions.values();
        for (CustomersTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }

}
