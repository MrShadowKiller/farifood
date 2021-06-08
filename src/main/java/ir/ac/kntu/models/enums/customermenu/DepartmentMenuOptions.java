package ir.ac.kntu.models.enums.customermenu;

public enum DepartmentMenuOptions {
    SHOW_INFORMATION(0), SELECT_ITEM(1), SHOW_COMMENTS(2), CHECKOUT(3), EXIT(4), DEFAULT(5);

    private int rate;

    DepartmentMenuOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public DepartmentMenuOptions findOption(int userInput) {
        DepartmentMenuOptions[] options = DepartmentMenuOptions.values();
        for (DepartmentMenuOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
