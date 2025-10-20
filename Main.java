import java.util.Scanner;

public class Main {
    private static Supermarket supermarket;
    private static Shopper shopper;
    private static final Scanner scanner = new Scanner(System.in);
    private static final int ROW_COUNT = 22;
    private static final int COL_COUNT = 22;

    public static void main(String[] args) {
        startSimulation();
    }

    private static void startSimulation() {
        int spawnpointRow = 21;
        int spawnpointCol = 11;

        System.out.println("=== SUPERMARKET SIMULATOR ===");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        shopper = new Shopper(name, age, spawnpointRow, spawnpointCol);
        supermarket = new Supermarket(shopper);

        for (int i = 0; i < supermarket.getFloors().size(); i++) {
            supermarket.getFloors().get(i).generateFloorTiles(ROW_COUNT, COL_COUNT);
        }

        supermarket.getFloors().get(0).moveShopper(shopper);
        
        System.out.println("\nWelcome, " + name + "! You're at the entrance.");
        if (age < 18) System.out.println("Note: Alcohol not allowed for minors (under 18)");
        if (age >= 60) System.out.println("Note: Senior citizen discount available on select items");
        
        runMainLoop();
    }

    
    private static void runMainLoop() {
        while (true) {
            supermarket.getFloors().get(0).displayFloorTiles(ROW_COUNT, COL_COUNT);
            System.out.println("Controls:");
            System.out.println("[Movement]   =  W : Move Up    | A : Move Left | S : Move Down  | D : Move Right");
            System.out.println("[Direction]  =  I : Look North | J : Look East | K : Look South | L : Look East");
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "W" -> shopper.move(choice);
                case "A" -> shopper.move(choice);
                case "S" -> shopper.move(choice);
                case "D" -> shopper.move(choice);
                case "I" -> shopper.look(choice);
                case "J" -> shopper.look(choice);
                case "K" -> shopper.look(choice);
                case "L" -> shopper.look(choice);
                case "Q" -> { 
                    System.out.println("Thank you for shopping!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /* 
    private static void showMenu() {
        System.out.println("\nMAIN MENU\n");
        System.out.println("Location: " + supermarket.getCurrentLocation());
        System.out.println("Carrying: " + shopper.getProductCount() + " items");
        System.out.println("Equipment: " + shopper.getEquipmentType());
        System.out.println("\nM - Move");
        System.out.println("I - Interact");
        System.out.println("P - View Products");
        System.out.println("Q - Quit");
        System.out.print("Choose: ");
    }
        */

    /* 
    private static void interact() {
        //Amenity amenity = supermarket.showAmenity();
        if (amenity != null) {
            amenity.interact(shopper);
        } else {
            System.out.println("Nothing to interact with here.");
        }
    }

    private static void viewProducts() {
        shopper.displayProducts();
    }
    */
    
}