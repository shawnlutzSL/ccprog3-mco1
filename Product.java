public class Product {
    private final String serialNumber;
    private final String name;
    private final double price;
    private final ProductType type;

    public Product(String serialNumber, String name, double price, ProductType type) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }
    
}
