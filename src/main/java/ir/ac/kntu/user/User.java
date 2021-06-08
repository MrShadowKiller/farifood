package ir.ac.kntu.user;

import ir.ac.kntu.management.ScannerWrapper;

public abstract class User extends Person {
    private String username;

    private String password;

    public User(String firstName, String lastName, String phoneNumber, String username, String password) {
        super(firstName, lastName, phoneNumber);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (checkPasswordValidation(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean checkPasswordValidation(String password) {
        String passwordValidation = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=." +
                "*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        return password.matches(passwordValidation);
    }

    public void changePassword() {
        System.out.print("New Password : ");
        String newPassword = ScannerWrapper.getInstance().nextLine().trim();
        if (checkPasswordValidation(newPassword)) {
            this.password = newPassword;
            return;
        }
        System.out.println("Invalid Password!");
    }


}
