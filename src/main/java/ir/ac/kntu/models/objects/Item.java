package ir.ac.kntu.models.objects;

import ir.ac.kntu.models.user.UserRate;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Item {
    private String name;

    private double price;

    private ArrayList<UserRate> itemRates;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        itemRates = new ArrayList<>();
    }

    public Item(String name) {
        this.name = name;
        itemRates = new ArrayList<>();
    }

    public Item(Item item) {
        this.name = item.name;
        this.price = item.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addItemRate(UserRate itemRate) {
        itemRates.add(itemRate);
    }

    public double getAverageRate() {
        if (itemRates.size() == 0) {
            return 5;
        }
        double average = 0;
        for (UserRate f : itemRates) {
            average += f.getRate();
        }
        return average / itemRates.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, itemRates);
    }
}
