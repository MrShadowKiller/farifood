package ir.ac.kntu;

import ir.ac.kntu.objects.Address;
import ir.ac.kntu.objects.Fruit;
import ir.ac.kntu.objects.Item;
import ir.ac.kntu.objects.Product;
import ir.ac.kntu.order.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class FruitMarket extends Department {
    private HashMap<Integer, Integer> ordersSchedule;

    public FruitMarket(String name, Address address, int workHoursOpen, int workHoursClose) {
        super(name, address, workHoursOpen, workHoursClose);
        for (int i=workHoursOpen;i<workHoursClose;i+=2){
            ordersSchedule.put(i,5);
        }
    }

    public ArrayList<Fruit> getFruits(){
        ArrayList<Fruit> fruits = new ArrayList<>();
        for (Item item : getItems() ) {
            if (item instanceof Fruit){
                fruits.add((Fruit) item);
            }
        }
        return fruits;
    }

}
