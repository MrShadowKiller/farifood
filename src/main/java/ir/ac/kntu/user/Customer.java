package ir.ac.kntu.user;

import ir.ac.kntu.Address;
import ir.ac.kntu.ScannerWrapper;
import ir.ac.kntu.order.Comment;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends Person {
    private String username;

    private String password;

    private Address address;

    private ArrayList<Order> orders;

    private ArrayList<Comment> comments;

    private Wallet wallet;

    private CreditCard creditCard;

    private UserSetting userSetting;

    public Customer(String firstName, String lastName, String phoneNumber,
                    String username, String password, Address address) {
        super(firstName, lastName, phoneNumber);
        this.username = username;
        this.password = password;
        this.address = address;
        orders = new ArrayList<>();
        wallet = new Wallet();
        if (this instanceof Admin){
            wallet.setBalance(99999999);
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
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
        if (checkPasswordValidation(password)){
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean checkPasswordValidation(String password){
        String passwordValidation = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=." +
                "*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        return password.matches(passwordValidation);
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

    public UserSetting getUserSetting() {
        return userSetting;
    }

    public void setUserSetting(UserSetting userSetting) {
        this.userSetting = userSetting;
    }

    public String briefInformation(){
        return "Full Name : " + getFirstName() + " " + getLastName()
                + "\n\tAddress : " + address;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void changePassword(){
        System.out.print("New Password : ");
        String newPassword = ScannerWrapper.getInstance().nextLine().trim();
        if (checkPasswordValidation(newPassword)){
            this.password = newPassword;
            return;
        }
        System.out.println("Invalid Password!");
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
