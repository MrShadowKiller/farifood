package ir.ac.kntu.objects;

public class Fruit extends Item implements StackableItem {
    private int stock = 0;

    public Fruit(String name, double price) {
        super(name, price);
    }

    public Fruit(String name) {
        super(name);
    }

    public Fruit(Fruit fruit) {
        super(fruit.getName(), fruit.getPrice());
    }

    public Fruit(Fruit fruit, double price, int stock) {
        super(fruit.getName(), price);
        this.stock = stock;
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
