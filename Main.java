import java.util.Scanner;

public class Main {
    private static Supermarket supermarket;
    private static Shopper shopper;
    private static final Scanner scanner = new Scanner(System.in);
    private static final int ROW_COUNT = 22;
    private static final int COL_COUNT = 22;
    private static boolean run = true;

    public static void main(String[] args) {
        while (run) {
            startSimulation();
            System.out.println("Would you like to restart the simulation? (Y to continue, anything else to exit like N)");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            run = choice.equalsIgnoreCase("Y");
        }

    }

    private static void startSimulation() {
        int spawnpointRow = 21;
        int spawnpointCol = 11;

        System.out.println("=== SUPERMARKET SIMULATOR ===");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        int age = 0;
        
        boolean isNameValid = false;
        while (!isNameValid) {
            try {
                System.out.print("Enter your age: ");
                age = scanner.nextInt();
                scanner.nextLine();
                isNameValid = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                isNameValid = false;
            }
        }
        
        shopper = new Shopper(name, age, spawnpointRow, spawnpointCol);
        supermarket = new Supermarket(shopper);

        for (int i = 0; i < supermarket.getFloors().size(); i++) {
            supermarket.getFloors().get(i).generateFloorTiles(ROW_COUNT, COL_COUNT);
            supermarket.getFloors().get(i).generateProducts();
        }
        
        System.out.println("\nWelcome, " + name + "! You're at the entrance.");
        if (age < 18) 
            System.out.println("Note: Alcohol and Cleaning Agents are not allowed for minors (under 18)");
        if (age >= 60)
            System.out.println("Note: Senior citizen discount available on select items: Non-Alcoholic Beverages and Food");
        
        runMainLoop();
    }

    
    private static void runMainLoop() {
        while (shopper.getRun()) {
            supermarket.getCurrentFloor().displayFloorTiles(ROW_COUNT, COL_COUNT);
            System.out.println("\nControls:");
            System.out.println("[Movement]    =  W : Move Up    | A : Move Left | S : Move Down  | D : Move Right");
            System.out.println("[Direction]   =  I : Look North | J : Look West | K : Look South | L : Look East");
            System.out.println("[Inventory]   =  M : View Items Stored");
            System.out.println("[Interaction] =  SpaceBar : Interact");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().toUpperCase();
            System.out.println();
            switch (choice) {
                case "W", "A", "S", "D" -> shopper.move(choice, supermarket.getCurrentFloor());
                case "I", "J", "K", "L" -> shopper.look(choice);
                case "M" -> {
                    shopper.displayProducts();
                    System.out.println("Enter any key to continue: ");
                    scanner.nextLine();
                }
                case " " -> {
                    shopper.interact(supermarket.getCurrentFloor(), scanner, supermarket);
                    System.out.println("Enter any key to continue: ");
                    scanner.nextLine();}
                case "Q" -> { 
                    System.out.println("Thank you for shopping!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

        }

    }
    
}