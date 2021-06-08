package ir.ac.kntu.models.user;

import ir.ac.kntu.models.objects.Address;
import ir.ac.kntu.models.objects.CreditCard;
import ir.ac.kntu.models.objects.Item;
import ir.ac.kntu.models.objects.Wallet;
import ir.ac.kntu.models.order.Comment;
import ir.ac.kntu.models.order.Order;
import ir.ac.kntu.models.user.setting.UserSetting;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends User {
    private Address address;

    private ArrayList<Order> orders;

    private ArrayList<Comment> comments;

    private Wallet wallet;

    private CreditCard creditCard;

    private boolean hasSubsription = false;

    private UserSetting userSetting;

    private final ArrayList<Item> basket;

    public Customer(String firstName, String lastName, String phoneNumber,
                    String username, String password, Address address) {
        super(firstName, lastName, phoneNumber, username, password);
        this.address = address;
        orders = new ArrayList<>();
        wallet = new Wallet();
        comments = new ArrayList<>();
        basket = new ArrayList<>();
        userSetting = new UserSetting();
        if (this instanceof Admin) {
            wallet.setBalance(99999999);
        }
    }

    public boolean isHasSubsription() {
        return hasSubsription;
    }


    public void setHasSubsription(boolean hasSubsription) {
        this.hasSubsription = hasSubsription;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
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

    public String briefInformation() {
        return "Full Name : " + getFirstName() + " " + getLastName()
                + "\n\tAddress : " + address;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addItemToBasket(Item item) {
        basket.add(item);
    }

    public void emptyBasket() {
        basket.clear();
    }

    public double getBasketCost() {
        double cost = 0;
        for (Item item : basket) {
            cost += item.getPrice();
        }
        return cost;
    }

    public ArrayList<Item> getBasket() {
        return basket;
    }

    @Override
    public String toString() {
        return "Customer Information {" +
                "\tUsername : " + getUsername() + '\n' +
                "\tpassword : " + getPassword() + '\n' +
                "\tName : " + getFirstName() + '\n' +
                "\tLastName : " + getLastName() + '\n' +
                "\tPhoneNumber :" + getPhoneNumber() + '\n' +
                "\tAddress :" + address + '\n' +
                "\tWallet balance : " + wallet.getBalance() + "$\n}";
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
        return address.equals(customer.address) && orders.equals(customer.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, orders);
    }
}
