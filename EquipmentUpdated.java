import java.util.*;

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
        if (products.size() < productCapacity) {
            products.add(product);
            return true;
        }
        return false;
    }

    public boolean removeProduct(Product product) {
        if (index >= 0 && index < products.size()) {
            return products.remove(product);
        }
        return false;
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
    
    public abstract void displayContents();

    protected void displayProductSummary() {
        Map<String, Integer> productCount = new HashMap<>();
        Map<String, Double> productTotalPrice = new HashMap<>();

        for (Product product : products) {
            productCount.put(product.getName(), productCount.getOrDefault(product.getName(), 0) + 1);
            productTotalPrice.put(product.getName(), productTotalPrice.getOrDefault(product.getName(), 0.0) + product.getPrice());
        }

        for (String productName : productCount.keySet()) {
            System.out.printf("  %s: %d items, Total: PHP %.2f\n", 
                productName, productCount.get(productName), productTotalPrice.get(productName));
    }

    double total = products.stream().mapToDouble(Product::getPrice).sum();
    System.out.printf("Total: PHP %.2f\n", total);
    }
}

class Basket extends Equipment {
    public Basket() {
        super("Basket", 15);
    }

    @Override
    public void displayContents() {
        System.out.println("\nBasket Contents\n");
        System.out.println("Items: " + getProductCount() + "/" + getCapacity());
        if (products.isEmpty()) {
            System.out.println("  (Empty)");
        } else {
            displayProductSummary();
        }
    }
}

class Cart extends Equipment {
    public Cart() {
        super("Cart", 50);
    }

    @Override
    public void displayContents() {
        System.out.println("\nCart Contents\n");
        System.out.println("Items: " + getProductCount() + "/" + getCapacity());
        if (products.isEmpty()) {
            System.out.println("  (Empty)");
        } else {
            displayProductSummary();
        }
    }
}