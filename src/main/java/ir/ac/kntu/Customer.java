package ir.ac.kntu;

import java.util.Objects;

public class Customer {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    public Customer(){
    }

    public Customer(String username, String password, String firstName,
                    String lastName, String phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
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
        String passwordValidation = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=." +
                "*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        if (password.matches(passwordValidation)){
            this.password = password;
            return true;
        }
        return false;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String briefInformation(){
        return "Full Name : " + firstName + " " + lastName
                + "\nAddress : " + address;
    }

    @Override
    public String toString() {
        return "Customer Information {" +
                "\tUsername= '" + username + '\n' +
                "\tpassword= '" + password + '\n' +
                "\tName= '" + firstName + '\n' +
                "\tLastName= '" + lastName + '\n' +
                "\tPhoneNumber= '" + phoneNumber + '\n' +
                "\tAddress= '" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return username.equals(customer.username) && password.equals(customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
