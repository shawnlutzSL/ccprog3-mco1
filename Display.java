import java.util.ArrayList;
import java.util.Scanner;

public class Display extends Amenity {
    //private DisplayAddress address;
    private final ArrayList<Product> products;
    private final int productCapacity;
    private final String address;
    private final String[] namesOfProductsForTable = {"Fruit", "Vegetable", "Bread", "Eggs"};
    private final String[] namesOfProductsForRefrigerator = {"Milk", "Frozen_Food", "Cheese"};
    private final String[] namesOfProductsForChilledCounter = {"Chicken", "Beef", "Seafood"};
    private final String[] namesOfProductsForShelf = {
    "Cereal", "Noodles", "Snacks", "Canned_Goods",
    "Condiments", "Soft_drink", "Juice", "Alcohol",
    "Cleaning_Agents", "Home_Essentials", "Hair_Care",
    "Body_Care", "Dental_Care", "Clothes", "Stationery", "Pet_Food"
    };

    public Display(String type, Tile location, String address) {
        super(type, location);
        this.address = address;
        this.products = new ArrayList<>();
        switch (type) {
            case "Table" -> {
                this.productCapacity = 4;
            }
            case "Chilled Counter" -> {
                this.productCapacity = 3;
            }
            case "Refrigerator" -> {
                this.productCapacity = 3 * 3;
            }
            case "Shelf" -> {
                this.productCapacity = 4 * 2;
            }
            default -> {
                this.productCapacity = 0;
            }
        }
    }

    @Override
    public void interact(Shopper shopper, Scanner scanner, Supermarket market) {
        switch (getType()) {
            case "Table" -> handleTable(shopper, scanner);
            case "Chilled Counter" -> handleChilledCounter(shopper, scanner);
            case "Refrigerator" -> handleRefrigerator(shopper, scanner);
            case "Shelf" -> handleShelf(shopper, scanner);
            default -> System.out.println("Using " + getType());
        }
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getProductCapacity() {
        return productCapacity;
    }

    public String[] getNamesOfProductsForTable() {
        return namesOfProductsForTable;
    }

    public String[] getNamesOfProductsForRefrigerator() {
        return namesOfProductsForRefrigerator;
    }

    public String[] getNamesOfProductsForChilledCounter() {
        return namesOfProductsForChilledCounter;
    }

    public String[] getNamesOfProductsForShelf() {
        return namesOfProductsForShelf;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    private void userChoice(Shopper shopper, Scanner scanner) {
        if (products.isEmpty())
            System.out.println("This display is empty, you can't take anymore!");
        else if (!products.isEmpty())
            System.out.println("\n(A) Take a product from the display");
        System.out.println("(B) Return a product to the display");
        System.out.println("(C) Continue");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().toUpperCase();
        System.out.println();
        
        switch (choice) {
            case "A" -> {
                if (!products.isEmpty()) {
                    System.out.println("Enter product to take, Example | 1");
                    System.out.print("Choice: ");
                    try {
                        int choiceInt = scanner.nextInt();
                        scanner.nextLine();

                        if (choiceInt < 1 || choiceInt > products.size()) {
                            System.out.println("Invalid choice. Please enter a valid product number between 1 and " + products.size());
                        } else {
                            Product product = products.get(choiceInt - 1);
                            if (shopper.getAge() < 18 && (product.getName().equalsIgnoreCase("ALCOHOL") || product.getName().equalsIgnoreCase("CLEANING_AGENTS"))) {
                                System.out.println("Alcohol and Cleaning Agents are not allowed for people under the age of 18. (Minors)");
                                return;
                            }
                            if (shopper.addProduct(product)) {
                                products.remove(product);
                            }
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid choice.");
                    }
                }
            }
            case "B" -> {
                if (shopper.getProductCount() != 0) {
                    if ((products.size() + 1) > productCapacity) {
                        System.out.println("The display is full, you cannot return a product here!");
                        return;
                    }
                    shopper.displayProducts();
                    System.out.println("Enter product to return, Example | 1");
                    System.out.print("Choice: ");
                    try {
                        int choiceInt = scanner.nextInt();
                        scanner.nextLine();

                        if (choiceInt < 1 || choiceInt > productCapacity) {
                            System.out.println("Invalid choice. Please enter a number between 1 and " + productCapacity);
                        } else {
                            shopper.removeProduct(choiceInt, products, this);
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Your inventory is empty, there is nothing to return!");
                }
            }
            case "C" -> {
                System.out.println("Moving on...");
                break;
            }
            default -> System.out.println("Invalid choice.");

        }
        
    }

    private void printContent() {
        System.out.printf("\n%-20s %-20s %-25s %-20s %10s%n", "Name", "Product", "Brand", "Serial", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        int i = 0;
        for (Product product : products) {
            i++;
            System.out.printf("%-20s %-20s %-25s %-20s PHP %8.2f%n", "(" + i + ") " +
            product.getName(), product.getType(), product.getBrand(), product.getSerialNumber(), product.getPrice());
        }
    }

    public void handleTable(Shopper shopper, Scanner scanner) {
        for (int i = 1; i <= products.size(); i++) {
            System.out.print(FontColor.apply(" " + i + " ", FontColor.BLACK, FontColor.BG_GREEN));
            if (i == 2)
                System.out.println();
        }
        
        printContent();    
        userChoice(shopper, scanner);
    }

    public void handleChilledCounter(Shopper shopper, Scanner scanner) {
        if (products.size() == 3) {
            System.out.print(FontColor.apply(" " + 1 + "   " + 2 + " \n   " + 3 + "   ", FontColor.BLACK, FontColor.BG_CYAN));
        } else if (products.size() != 3) {
            for (int i = 1; i <= products.size(); i++) {
                System.out.print(FontColor.apply(" " + i + " ", FontColor.BLACK, FontColor.BG_CYAN));
            }
        }
        
        printContent();
        userChoice(shopper, scanner);
    }

    public void handleRefrigerator(Shopper shopper, Scanner scanner) {
        for (int i = 1; i <= products.size(); i++) {
            System.out.print(FontColor.apply(" " + i + " ", FontColor.BLACK, FontColor.BG_BLUE));
            if (i == 3 || i == 6)
                System.out.println();
        }

        printContent(); 
        userChoice(shopper, scanner);
    }

    public void handleShelf(Shopper shopper, Scanner scanner) {
        for (int i = 1; i <= products.size(); i++) {
            System.out.print(FontColor.apply(" " + i + " ", FontColor.BLACK, FontColor.BG_YELLOW));
            if (i == 4)
                System.out.println();
        }
        
        printContent();
        userChoice(shopper, scanner);
    }

}