package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends Person{
    private String username;

    private String password;

    private String address;

    private ArrayList<Order> orders;

    public Customer(String firstName, String lastName, String phoneNumber,
                    String username, String password, String address) {
        super(firstName, lastName, phoneNumber);
        this.username = username;
        this.password = password;
        this.address = address;
        orders = new ArrayList<>();
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String briefInformation(){
        return "Full Name : " + getFirstName() + " " + getLastName()
                + "\n\tAddress : " + address;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    @Override
    public String toString() {
        return "Customer Information {" +
                "\tUsername= '" + username + '\n' +
                "\tpassword= '" + password + '\n' +
                "\tName= '" + getFirstName() + '\n' +
                "\tLastName= '" + getLastName() + '\n' +
                "\tPhoneNumber= '" + getPhoneNumber() + '\n' +
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
