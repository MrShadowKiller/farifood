package ir.ac.kntu.user;

import ir.ac.kntu.Address;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends Person {
    private String username;

    private String password;

    private Address address;

    private ArrayList<Order> orders;

    private Wallet wallet;

    private CreditCard creditCard;

    public Customer(String firstName, String lastName, String phoneNumber,
                    String username, String password, Address address, CreditCard creditCard) {
        super(firstName, lastName, phoneNumber);
        this.username = username;
        this.password = password;
        this.address = address;
        this.creditCard = creditCard;
        orders = new ArrayList<>();
        wallet = new Wallet();
    }

    //Admin Constructor
    public Customer(String firstName, String lastName, String phoneNumber,
                    String username, String password, Address address) {
        super(firstName, lastName, phoneNumber);
        this.username = username;
        this.password = password;
        this.address = address;
        orders = new ArrayList<>();
        wallet = new Wallet();
        creditCard = new CreditCard();
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


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
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
                "\tAddress= '" + address + '\n' +
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
