package ir.ac.kntu.user;

import ir.ac.kntu.Department;

public class SellerMan extends Person{
    private Department department;

    public SellerMan(String firstName, String lastName, String phoneNumber, Department department) {
        super(firstName, lastName, phoneNumber);
        this.department = department;
    }

    public SellerMan(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
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
