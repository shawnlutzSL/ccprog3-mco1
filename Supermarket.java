//display.java

import java.util.ArrayList;
import java.util.List;

public abstract class Display {
    protected String address;
    protected String displayType;
    protected List<Product> products;
    protected int capacity;

    public Display(String address, String displayType, int capacity) {
        this.address = address;
        this.displayType = displayType;
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
        return new ArrayList<>(products);
    }

    public boolean hasSpace() {
        return products.size() < capacity;
    }

    public String getAddress() { return address; }
    public String getDisplayType() { return displayType; }
    public int getProductCount() { return products.size(); }
    public int getCapacity() { return capacity; }

    public abstract void displayInfo();
}

//shopper.java

import java.util.*;

public class Shopper {
    private String name;
    private int age;
    private Equipment equipment;
    private List<Product> handCarried;

    public Shopper(String name, int age) {
        this.name = name;
        this.age = age;
        this.handCarried = new ArrayList<>();
    }

    public boolean canGetAlcohol() {
        return age >= 18;
    }

    public boolean isSeniorCitizen() {
        return age >= 60;
    }

    public double applyDiscount(Product product) {
    if (isSeniorCitizen()) {
        String type = product.getType();
        if (type.equals("Soft drink") || type.equals("Juice")) {
            return product.getPrice() * 0.9;
        } else if (!type.equals("Alcohol") && isConsumable(type)) { //excluded in discount based on pd
            return product.getPrice() * 0.8;
        }
    }
    return product.getPrice();
    }

    private boolean isConsumable(String type) { //consumables allowed for discount
        String[] consumables = {
            "Fruit", "Cereal", "Noodles", "Snacks", 
            "Canned Goods", "Condiments", "Soft drink", "Juice"
        };
        return Arrays.asList(consumables).contains(type);
    }

    public boolean addProduct(Product product) {
        if (equipment != null) {
            if (!equipment.isFull()) {
                return equipment.addProduct(product);
            }
        } else if (handCarried.size() < 2) {
            handCarried.add(product);
            return true;
        }
        return false;
    }

    public void setEquipment(Equipment equipment) {
        if (handCarried.isEmpty()) {
            this.equipment = equipment;
        }
    }

    public void removeEquipment() {
        if (equipment != null && equipment.isEmpty()) {
            this.equipment = null;
        }
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public boolean hasEquipment() {
        return equipment != null;
    }

    public String getEquipmentType() {
        return equipment != null ? equipment.getType() : "None";
    }

    public int getProductCount() {
        if (equipment != null) {
            return equipment.getProductCount();
        }
        return handCarried.size();
    }

    public void displayProducts() {
        if (hasEquipment()) {
            equipment.displayContents();
        } else {
            System.out.println("Hand-carried Products\n");
            System.out.println("Items: " + handCarried.size() + "/2");
            if (handCarried.isEmpty()) {
                System.out.println("  (Empty)");
            } else {
                for (int i = 0; i < handCarried.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + handCarried.get(i));
                }
                double total = handCarried.stream().mapToDouble(Product::getPrice).sum();
                System.out.printf("TOTAL:  %.2f\n", total);
            }
        }
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<Product> getHandCarried() { return new ArrayList<>(handCarried); }
}

//amenity.java

public abstract class Amenity {
    protected String type;
    protected String location;

    public Amenity(String type, String location) {
        this.type = type;
        this.location = location;
    }

    public abstract void interact(Shopper shopper);

    public String getType() { return type; }
    public String getLocation() { return location; }
}

//main.java

import java.util.Scanner;

public class Main {
    private static Supermarket supermarket;
    private static Shopper shopper;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startSimulation();
    }

    private static void startSimulation() {
        System.out.println("=== SUPERMARKET SIMULATOR ===");
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        shopper = new Shopper(name, age);
        supermarket = new Supermarket();
        
        System.out.println("\nWelcome, " + name + "! You're at the entrance.");
        if (age < 18) System.out.println("Note: Alcohol not allowed for minors (under 18)");
        if (age >= 60) System.out.println("Note: Senior citizen discount available on select items");
        
        runMainLoop();
    }

    private static void runMainLoop() {
        while (true) {
            showMenu();
            String choice = scanner.nextLine().toUpperCase();
            
            switch (choice) {
                case "M": move(); break;
                case "I": interact(); break;
                case "P": viewProducts(); break;
                case "Q": 
                    System.out.println("Thank you for shopping!");
                    return;
                default: 
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

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

    private static void move() {
        System.out.print("Direction (WASD): ");
        String dir = scanner.nextLine();
        supermarket.moveShopper(dir);
    }

    private static void interact() {
        Amenity amenity = supermarket.showAmenity();
        if (amenity != null) {
            amenity.interact(shopper);
        } else {
            System.out.println("Nothing to interact with here.");
        }
    }

    private static void viewProducts() {
        shopper.displayProducts();
    }
}