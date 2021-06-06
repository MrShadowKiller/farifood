package ir.ac.kntu.objects;

public class Fruit extends Item implements Stackable {
    private int stock = 0;

    public Fruit(String name, double price) {
        super(name, price);
    }

    public Fruit(String name) {
        super(name);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean isOutOfStock() {
        return stock == 0;
    }
}
