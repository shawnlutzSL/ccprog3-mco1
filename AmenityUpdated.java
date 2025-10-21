public abstract class Amenity {
    protected String type;
    protected Tile location;

    public Amenity(String type, Tile location) {
        this.type = type;
        this.location = location;
    }

    public abstract void interact(Shopper shopper);

    public String getType() {
        return type;
    }

    public Tile getLocation() {
        return location;
    }
}

class DisplayAmenity extends Amenity {
    private Display display;

    public DisplayAmenity(Tile location, Display display) {
        super("Display", location);
        this.display = display;
    }

    @Override
    public void interact(Shopper shopper) {
        System.out.println("Interacting with display " + display.getDisplayType() + " at " + display.getAddress());
        display.displayInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Take products");
        System.out.println("2. Go Back");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            takeProduct(shopper, scanner);
        }
    }

    private void takeProduct(Shopper shopper, Scanner scanner) {
        if (display.getProductCount() == 0) {
            System.out.println("No products available on this display.");
            return;
        }

        System.out.print("Enter product number: ");
        try {
            int num = Integer.parseInt(scanner.nextLine());
            Product product = display.removeProduct(num - 1);
            
            if (product != null) {
                if (product.getType().equals("Alcohol") && !shopper.canGetAlcohol()) {
                    System.out.println("Too young for alcohol!");
                    display.addProduct(product);
                } else if (shopper.addProduct(product)) {
                    double price = shopper.applyDiscount(product);
                    System.out.println("Took: " + product.getName() + " for PHP " + price);
                } else {
                    System.out.println("No space!");
                    display.addProduct(product);
                }
            } else {
                System.out.println("Invalid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number.");
        }
    }

    public Display getDisplay() {
        return display;
    }
}

class ServiceAmenity extends Amenity {
    public ServiceAmenity(String type, Tile location) {
        super(type, location);
    }

    @Override
    public void interact(Shopper shopper) {
        switch (getType()) {
            case "Basket Station":
                handleBasket(shopper);
                break;
            case "Cart Station":
                handleCart(shopper);
                break;
            case "Checkout Counter":
                handleCheckout(shopper);
                break;
            case "Entrance":
                System.out.println("You're at the entrance.");
                break;
            case "Product Search":
                System.out.println("Product search - MCO2 feature");
                break;
            default:
                System.out.println("Using " + getType());
        }
    }

    private void handleBasket(Shopper shopper) {
        if (!shopper.hasEquipment() && shopper.getHandCarried().isEmpty()) {
            shopper.setEquipment(new Basket());
            System.out.println("Got a basket!");
        } else if (shopper.hasEquipment() && shopper.getEquipment() instanceof Basket 
                   && shopper.getEquipment().isEmpty()) {
            shopper.removeEquipment();
            System.out.println("Returned basket.");
        } else {
            System.out.println("Can't get/return basket now.");
        }
    }

    private void handleCart(Shopper shopper) {
        if (!shopper.hasEquipment() && shopper.getHandCarried().isEmpty()) {
            shopper.setEquipment(new Cart());
            System.out.println("Got a cart!");
        } else if (shopper.hasEquipment() && shopper.getEquipment() instanceof Cart 
                   && shopper.getEquipment().isEmpty()) {
            shopper.removeEquipment();
            System.out.println("Returned cart.");
        } else {
            System.out.println("Can't get/return cart now.");
        }
    }

    private void handleCheckout(Shopper shopper) {
        if (shopper.getProductCount() == 0) {
            System.out.println("No products to checkout.");
            return;
        }
        
        System.out.println("=== CHECKOUT ===");
        shopper.displayProducts();
        System.out.println("Thank you for shopping!");
        
        if (shopper.hasEquipment()) {
            shopper.removeEquipment();
        }
    }
}

class Tile {
    private int x;
    private int y;
    private String description;

    public Tile(int x, int y, String description) {
        this.x = x;
        this.y = y;
        this.description = description;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return description + " (" + x + "," + y + ")";
    }
}