package ir.ac.kntu.user;

public class CreditCard {
    private String cardNumber;

    private String password;

    private double balance;

    public CreditCard(String cardNumber, String password, double balance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void useBalance(double balance){
        this.balance -= balance;
    }

    public void addBalance(double balance){
        this.balance += balance;
    }

}
