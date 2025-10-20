import java.util.ArrayList;
import java.util.List;

public abstract class Display extends Amenity{
    protected List<Product> products;
    protected DisplayAddress address;
    protected int capacity;

    public Display(String type, Tile location, DisplayAddress address, int capacity) {
        super(type, location);
        this.address = address;
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        if (products.size() < capacity) {
            products.add(product);
            return true;
        }
        
        return false;
    }

    public Product removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            return products.remove(index);
        }

        return null;
    }

    public List<Product> getProducts() {
        //return new ArrayList<>(products);
        return products;
    }

    public boolean hasSpace() {
        return products.size() < capacity;
    }

    public DisplayAddress getAddress() {
        return address;
    }

    public int getProductCount() {
        return products.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract void displayInfo();
}