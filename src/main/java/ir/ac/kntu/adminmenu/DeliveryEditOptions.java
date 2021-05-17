package ir.ac.kntu.adminmenu;

public enum DeliveryEditOptions {
    CHANGE_SALARY(0),CHANGE_VEHICLE(1),CHANGE_SALARY_TYPE(2),EXIT(3),
    DEFAULT(4);

    private int rate;

    DeliveryEditOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public DeliveryEditOptions findOption(int userInput){
        DeliveryEditOptions[] options = DeliveryEditOptions.values();
        for (DeliveryEditOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
