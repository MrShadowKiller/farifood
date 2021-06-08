package ir.ac.kntu.models.user;

import ir.ac.kntu.models.departments.Department;

public class SellerMan extends User {
    private Department department;

    public SellerMan(String username, String password, String firstName,
                     String lastName, String phoneNumber, Department department) {
        super(username, password, firstName, lastName, phoneNumber);
        this.department = department;
    }

    public SellerMan(String username, String password, String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber, username, password);
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String toString() {
        return "Full Name : " + getFirstName() + " " + getLastName() +
                "\n\tPhoneNumber : " + getPhoneNumber();
    }
}
