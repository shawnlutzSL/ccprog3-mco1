import java.util.ArrayList;
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

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getProductCount() {
        return products.size();
    }

    public boolean isFull() {
        return products.size() >= productCapacity;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return productCapacity;
    }

    public boolean addProduct(Product product) {
        if (products.size() < productCapacity) {
            products.add(product);
            System.out.println("Successfully added " + product.getName() + "!");
            return true;
        }

        return false;
    }

    public boolean removeProduct(Product product) {
        if (!products.isEmpty()) {
            products.remove(product);
            System.out.println("Successfully removed " + product.getName() + " from " + getType() + "!");
            return true;
        }

        System.out.println("Could not remove product!");
        return false;
    }

    public void displayContents() {
        System.out.println(getType() + " Contents");
        System.out.println("Item Count: " + getProductCount() + "/" + getCapacity());
        if (products.isEmpty()) {
            System.out.println("  (Empty)");
        } else {
            System.out.println("Item Summary: ");
            int i = 0;
            for (Product product : products) {
                i++;
                System.out.println(i + ". " + product.getName() + " | " + product.getBrand() + " | " + product.getPrice());
            }
        }
    }
}

class Basket extends Equipment {
    public Basket() {
        super(15, new ArrayList<>(), "Basket");
    }

}

class Cart extends Equipment {
    public Cart() {
        super(30, new ArrayList<>(), "Cart");
    }

}