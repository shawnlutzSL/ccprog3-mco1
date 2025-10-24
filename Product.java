public class Product {
    private final String serialNumber;
    private final String name;
    private final String type;
    private final String brand;
    private final double price;
    private final boolean beverageCheck;
    private final boolean consumableCheck;
    

    public Product(String serialNumber, String name, double price, String type, String brand, boolean beverageCheck, boolean consumableCheck) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.type = type;
        this.brand = brand;
        this.beverageCheck = beverageCheck;
        this.consumableCheck = consumableCheck;
    }

    public boolean isBeverage() {
        return beverageCheck;
    }

    public boolean isConsumable() {
        return consumableCheck;
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

    public String getType() {
        return type;
    }
    
    public String getBrand() {
        return brand;
    }

}
