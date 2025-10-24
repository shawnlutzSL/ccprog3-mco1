import java.util.Scanner;

public class Service extends Amenity {
    public Service(String type, Tile location) {
        super(type, location);
    }

    @Override
    public void interact(Shopper shopper, Scanner scanner, Supermarket market) {
        switch (getType()) {
            case "Basket Station" -> handleBasket(shopper);
            case "Cart Station" -> handleCart(shopper);
            case "Checkout Counter" -> handleCheckout(shopper);
            case "Stairs" -> handleStairs(market);
            case "Entrance" -> System.out.println("You're at the entrance.");
            case "Exit" -> handleExit(shopper);
            case "Product Search" -> handleProductSearch(market, scanner);
            default -> System.out.println("Using " + getType());
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
        Receipt receipt = new Receipt(shopper);
        receipt.computeTotal();
        System.out.println("Thank you for shopping! You may now exit the supermarket if you wish to do so!");
        
        if (shopper.hasEquipment()) {
            shopper.removeEquipment();
        }
    }

    private void handleStairs(Supermarket market) {
        System.out.println(market.getCurrentFloor().getFloorName());
        if (market.getCurrentFloor().getFloorName().equals("GF")) {
            System.out.println("Going up..!");
            market.setCurrentFloor(market.getFloors().get(1));
        } else if (market.getCurrentFloor().getFloorName().equals("2F")) {
            System.out.println("Going down..!");
            market.setCurrentFloor(market.getFloors().get(0));
        }

        System.out.println(market.getCurrentFloor().getFloorName());
    }

    private void handleExit(Shopper shopper) {
        if (shopper.getProductCount() != 0 && shopper.getEquipment() != null && shopper.isCheckedOut() == false) {
            System.out.println("You may not exit the supermarket.\nYou must checkout those items or put back the equipment first");
            return;
        } else if (shopper.getEquipment() == null && shopper.getProductCount() != 0 && shopper.isCheckedOut() == false) {
            System.out.println("You may not exit the supermarket.\nYou must checkout those items or put back the equipment first");
            return;
        } else if (shopper.getEquipment() != null && shopper.getProductCount() == 0 && shopper.isCheckedOut() == false) {
            System.out.println("You may not exit the supermarket.\nYou must checkout those items or put back the equipment first");
            return;
        }

        System.out.println("Hope you come back soon fellow shopper, bye bye now!");
        shopper.setRun(false);
    }

    private void handleProductSearch(Supermarket market, Scanner scanner) {
        System.out.print("\nEnter name of product to search for: ");
        String choice = scanner.nextLine();
        boolean found = false;

        for (Display display : market.getCurrentFloor().getDisplays()) {
            for (Product product : display.getProducts()) {
                if (choice.equalsIgnoreCase(product.getName())) {
                    System.out.println(display.getAddress());
                    found = true;
                }
                    
            }

        }

        if (!found)
            System.out.println("Product not found, it is either out of stock or we don't sell it!");

    }

}