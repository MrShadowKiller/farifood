package ir.ac.kntu.enums.customermenu;

public enum AddBalanceOptions {
    ADD_CREDIT_CARD(0), ADD_WALLET_BALANCE(1), ADD_CREDIT_CARD_BALANCE(2),
    ACTIVATE_SUBSCRIPTION(3), EXIT(4), DEFAULT(5);

    private int rate;

    AddBalanceOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public AddBalanceOptions findOption(int userInput) {
        AddBalanceOptions[] options = AddBalanceOptions.values();
        for (AddBalanceOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
