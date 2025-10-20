import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shopper {
    private final String name;
    private final int age;
    private int row;
    private int col;
    private String direction;
    private Equipment equipment;
    private final List<Product> handCarried;

    public Shopper(String name, int age, int row, int col) {
        this.name = name;
        this.age = age;
        this.row = row;
        this.col = col;
        this.direction = "NORTH";
        this.handCarried = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean canGetAlcohol() {
        return age >= 18;
    }

    public boolean isSeniorCitizen() {
        return age >= 60;
    }

    public void move(String direction) {
        switch (direction) {
            case "W" -> {
                row += 1;
                System.out.println("Moved up!");
            }
            case "A" -> {
                col -= 1;
                System.out.println("Moved left!");
            }
            case "S" -> {
                row -= 1;
                System.out.println("Moved down!");
            }
            case "D" -> {
                col += 1;
                System.out.println("Moved right!");
            }
        }
    }

    public void look(String direction) {
        switch (direction) {
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
                System.out.println("Looking east");
            }
        }
    }

    public double applyDiscount(Product product) {
    if (isSeniorCitizen()) {
        //String type = product.getType();
        //if (type.equals("Soft drink") || type.equals("Juice")) {
            //return product.getPrice() * 0.9;
        //} else if (!type.equals("Alcohol") && isConsumable(type)) { //excluded in discount based on pd
            //return product.getPrice() * 0.8;
        //}
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
        //if (equipment != null && equipment.isEmpty()) {
            this.equipment = null;
        //}
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
            //return equipment.getProductCount();
        }
        return handCarried.size();
    }

    public void displayProducts() {
        if (hasEquipment()) {
            //equipment.displayContents();
        } else {
            System.out.println("Hand-carried Products\n");
            System.out.println("Items: " + handCarried.size() + "/2");
            if (handCarried.isEmpty()) {
                System.out.println("  (Empty)");
            } else {
                for (int i = 0; i < handCarried.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + handCarried.get(i));
                }
                //double total = handCarried.stream().mapToDouble(Product::getPrice).sum();
                //System.out.printf("TOTAL:  %.2f\n", total);
            }
        }
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public List<Product> getHandCarried() { return new ArrayList<>(handCarried); }
}