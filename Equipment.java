import java.util.List;

public abstract class Equipment {
    protected int productCapacity;
    protected List<Product> products;
    protected String type;

    public Equipment(int productCapacity, List<Product> products, String type) {
        this.productCapacity = productCapacity;
        this.products = products;
        this.type = type;
    }

    public boolean addProduct(Product product) {
        return true;
    }

    public boolean removeProduct(Product product) {
        return true;
    }

    public boolean isFull() {
        return true;
    }

    public String getType() {
        return type;
    }
    
}