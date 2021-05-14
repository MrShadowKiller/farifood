package ir.ac.kntu.user;

import ir.ac.kntu.Address;

public class Admin extends Customer {

    public Admin(String firstName, String lastName, String phoneNumber,
                 String username, String password, Address address) {
        super(firstName, lastName, phoneNumber, username, password, address);
    }


}
