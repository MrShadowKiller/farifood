package ir.ac.kntu.adminmenu;

public enum DeliveriesTabOptions {
    ADD_DELIVERY(0),REMOVE_DELIVERY(1),VIEW_EDIT_DELIVERIES(2),
    VIEW_ORDERS(3),EXIT(4), DEFAULT(5);

    private int rate;

    DeliveriesTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public DeliveriesTabOptions findOption(int userInput){
        DeliveriesTabOptions[] options = DeliveriesTabOptions.values();
        for (DeliveriesTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
