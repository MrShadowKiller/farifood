package ir.ac.kntu;

import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.objects.Product;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class Supermarket extends Department {
    private ArrayList<Product> products;

    private HashMap<String, Order> orders;

    public Supermarket(String name, Address address, int workHoursOpen, int workHoursClose) {
        super(name, address, workHoursOpen, workHoursClose);
    }


    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        for (Item item : getItems() ) {
            if (item instanceof Product){
                products.add((Product) item);
            }
        }
        return products;
    }

}
