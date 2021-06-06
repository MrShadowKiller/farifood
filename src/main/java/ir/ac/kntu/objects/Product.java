package ir.ac.kntu.objects;

public class Product extends Item implements Stackable {
    private int stock = 0;

    public Product(String name, double price) {
        super(name, price);
    }

    public Product(String name) {
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
