package ir.ac.kntu.models.user;

import ir.ac.kntu.models.objects.Address;

public class Admin extends Customer {

    public Admin(String firstName, String lastName, String phoneNumber,
                 String username, String password, Address address) {
        super(firstName, lastName, phoneNumber, username, password, address);
    }

}
