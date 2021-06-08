package ir.ac.kntu.models.objects;

public class Product extends Item implements StackableItem {
    private int stock = 0;

    public Product(String name, double price) {
        super(name, price);
    }

    public Product(String name) {
        super(name);
    }

    public Product(Product product) {
        super(product.getName(), product.getPrice());
    }

    public Product(Product product, double price, int stock) {
        super(product.getName(), price);
        this.stock = stock;
    }

    public Product(Product product,int stock) {
        super(product.getName());
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
