package ir.ac.kntu.enums.adminmenu;

public enum AdminsTabOptions {
    ADD_ADMIN(0), REMOVE_ADMIN(1), VIEW_EDIT_ADMIN(2), EXIT(3),
    DEFAULT(4);

    private int rate;

    AdminsTabOptions(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static public AdminsTabOptions findOption(int userInput) {
        AdminsTabOptions[] options = AdminsTabOptions.values();
        for (AdminsTabOptions option : options) {
            if (option.getRate() == userInput - 1) {
                return option;
            }
        }
        return DEFAULT;
    }
}
