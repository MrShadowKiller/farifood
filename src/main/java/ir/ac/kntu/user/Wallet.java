package ir.ac.kntu.user;

public class Wallet {
    private double balance = 0;

    public Wallet(){
    }

    public Wallet(double balance){
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double value){
        balance += value;
    }

    public void useBalance(double value){
        balance -= value;
    }

}
