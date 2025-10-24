import java.util.ArrayList;
import java.util.Scanner;

public class Shopper {
    private final String name;
    private final int age;
    private int row;
    private int col;
    private String direction;
    private Equipment equipment;
    private final ArrayList<Product> handCarried;
    private boolean checkedOut;
    private boolean run;

    public Shopper(String name, int age, int row, int col) {
        this.name = name;
        this.age = age;
        this.row = row;
        this.col = col;
        this.direction = "north";
        this.handCarried = new ArrayList<>();
        this.checkedOut = false;
        this.run = true;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean newCheckedOut) {
        this.checkedOut = newCheckedOut;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean getRun() {
        return run;
    }

    public void setRun(boolean newRun) {
        this.run = newRun;
    }

    public boolean canGetAlcohol() {
        return age >= 18;
    }

    public boolean isSeniorCitizen() {
        return age >= 60;
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

    public String getName() { 
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Product> getHandCarried() {
        return new ArrayList<>(handCarried);
    }

    public void move(String direction, Floor floor) {
        Tile[][] tiles = floor.getTiles();
        switch (direction) {
            case "W" -> {
                if (tiles[row - 1][col].IsWalkable()) {
                    tiles[row][col].setText("   ");
                    row -= 1;
                    tiles[row][col].setText(" S ");
                    System.out.println("Moved up!");
                } else {
                    System.out.println("Can't move there, something is in the way!");
                }
            }
            case "A" -> {
                if (tiles[row][col - 1].IsWalkable()) {
                    tiles[row][col].setText("   ");
                    col -= 1;
                    tiles[row][col].setText(" S ");
                    System.out.println("Moved left!");
                } else {
                    System.out.println("Can't move there, something is in the way!");
                }
            }
            case "S" -> {
                if (row + 1 == 22) {
                    System.out.println("Can't move, that's out of bounds!");
                    return;
                }

                if (tiles[row + 1][col].IsWalkable()) {
                    tiles[row][col].setText("   ");
                    row += 1;
                    tiles[row][col].setText(" S ");
                    System.out.println("Moved down!");
                } else {
                    System.out.println("Can't move there, something is in the way!");
                }
            }
            case "D" -> {
                if (tiles[row][col + 1].IsWalkable()) {
                    tiles[row][col].setText("   ");
                    col += 1;
                    tiles[row][col].setText(" S ");
                    System.out.println("Moved right!");
                } else {
                    System.out.println("Can't move there, something is in the way!");
                }
            }
        }
    }

    public void look(String choice) {
        switch (choice) {
            case "I" -> {
                direction = "north";
                System.out.println("Looking north!");
            }
            case "J" -> {
                direction = "west";
                System.out.println("Looking west!");
            }
            case "K" -> {
                direction = "south";
                System.out.println("Looking south!");
            }
            case "L" -> {
                direction = "east";
                System.out.println("Looking east!");
            }
        }
    }

    public void interact(Floor floor, Scanner scanner, Supermarket market) {
        Tile[][] tiles = floor.getTiles();
        Tile currentTile = tiles[0][0];
        
        switch (direction) {
            case "north" -> currentTile = tiles[row - 1][col];
            case "east"  -> currentTile = tiles[row][col + 1];
            case "south" -> currentTile = tiles[row + 1][col];
            case "west"  -> currentTile = tiles[row][col - 1];
        }

        String currentType = currentTile.getTileType();
        System.out.println(currentType);

        for (String serviceName : floor.getNamesOfServices()) {
            if (currentType.equals(serviceName)) {
                Service currentService = floor.getServices().get(0);
                for (Service service : floor.getServices()) {
                    if (service.location == currentTile)
                        currentService = service;
                }

                currentService.interact(this, scanner, market);
            }
        }

        for (String displayName : floor.getNamesOfDisplays()) {
            if (currentType.equals(displayName)) {
                Display currentDisplay = floor.getDisplays().get(0);
                for (Display display : floor.getDisplays()) {
                    if (display.location == currentTile)
                        currentDisplay = display;
                }
                
                currentDisplay.interact(this, scanner, market);
            }
        }

    }

    public boolean addProduct(Product product) {
        if (equipment != null) {
            if (!equipment.isFull()) {
                return equipment.addProduct(product);
            }
        } else if (handCarried.size() < 2) {
            handCarried.add(product);
            System.out.println("Success! Currently holding the following: ");
            for (Product handCarriedProduct : handCarried) {
                System.out.print(handCarriedProduct.getName() + " | ");
            }
            return true;
        } else {
            System.out.println("Your hands are full! Cannot take product.");
        }

        return false;
    }

    public boolean removeProduct(int i, ArrayList<Product> products, Display currentDisplay) {
        if (equipment != null) {
            Product product = equipment.getProducts().get(i - 1);
            boolean canRemove = false;
            switch (currentDisplay.getType()) {
                case "Table" -> {
                    for (String tableName : currentDisplay.getNamesOfProductsForTable()) {
                        if (tableName.equalsIgnoreCase(product.getName()))
                            canRemove = true;
                    }
                }
                case "Chilled Counter" -> {
                    for (String chilledCounterName : currentDisplay.getNamesOfProductsForChilledCounter()) {
                        if (chilledCounterName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                        }
                    }
                }

                case "Refrigerator" -> {
                    for (String refrigeratorName : currentDisplay.getNamesOfProductsForRefrigerator()) {
                        if (refrigeratorName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                        }
                    }
                }

                case "Shelf" -> {
                    for (String shelfName : currentDisplay.getNamesOfProductsForShelf()) {
                        if (shelfName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                        }
                    }
                }
            }
            if (canRemove) {
                products.add(product);
                return equipment.removeProduct(product);
            }
        } else if (!handCarried.isEmpty()) {
            Product product = handCarried.get(i - 1);
            boolean canRemove = false;
            switch (currentDisplay.getType()) {
                case "Table" -> {
                    for (String tableName : currentDisplay.getNamesOfProductsForTable()) {
                        if (tableName.equalsIgnoreCase(product.getName()))
                            canRemove = true;
                    }
                }
                case "Chilled Counter" -> {
                    for (String chilledCounterName : currentDisplay.getNamesOfProductsForChilledCounter()) {
                        if (chilledCounterName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                        }
                    }
                }

                case "Refrigerator" -> {
                    for (String refrigeratorName : currentDisplay.getNamesOfProductsForRefrigerator()) {
                        if (refrigeratorName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                        }
                    }
                }

                case "Shelf" -> {
                    for (String shelfName : currentDisplay.getNamesOfProductsForShelf()) {
                        if (shelfName.equalsIgnoreCase(product.getName())) {
                            canRemove = true;
                        }
                    }
                }
            }
            if (canRemove) {
                products.add(product);
                handCarried.remove(product);
                System.out.println("Successfully not holding " +  product.getName() + " anymore!");
                for (Product handCarriedProduct : handCarried) {
                    System.out.print(handCarriedProduct.getName() + " | ");
                }
                return true;
            } else {
                System.out.println("You may not return that product here as it is not its respective display.");
            }
            
        } else {
            System.out.println("There's nothing to return!");
        }

        return false;
    }

    public void displayProducts() {
        if (equipment == null) {
            System.out.println("Hand-Carried Contents");
            System.out.println("Item Count: " + getProductCount() + "/2");
            if (handCarried.isEmpty()) {
                System.out.println("(Empty)");
            } else {
                System.out.println("Item Summary: ");
                int i = 0;
                for (Product product : handCarried) {
                    i++;
                    System.out.println(i + ". " + product.getName() + " | " + product.getBrand() + " | PHP " + product.getPrice() + " | " + product.getSerialNumber());
                }

            }

        } else {
            System.out.println("Equipment Contents");
            System.out.println("Item Count: " + getProductCount() + "/" + equipment.getCapacity());

            if (equipment.getProductCount() == 0) {
                System.out.println("(Empty)");
            } else {
                System.out.println("Item Summary: ");
                int i = 0;
                for (Product product : equipment.getProducts()) {
                    i++;
                    System.out.println(i + ". " + product.getName() + " | " + product.getBrand() + " | PHP " + product.getPrice() + " | " + product.getSerialNumber());
                }

            }
        }
        
    }

}