import java.util.ArrayList;
import java.util.Random;

public class Floor {
    private final String floorName;
    private final int floorNumber;
    private final Tile[][] tiles;
    private final char[][] tileTypes;
    private final String[] namesOfServices = {"Cart Station", "Basket Station", "Product Search", "Stairs", "Checkout Counter", "Exit"};
    private final String[] namesOfDisplays = {"Table", "Chilled Counter", "Refrigerator", "Shelf"};
    private final ArrayList<Service> services;
    private final ArrayList<Display> displays;
    private static int count;
    private final int MIN_PRICE = 50;
    private final int MAX_PRICE = 500;

    public Floor(String floorName, int floorNumber, char[][] tileTypes) {
        this.floorName = floorName;
        this.floorNumber = floorNumber;
        this.tiles = new Tile[22][22];
        this.tileTypes = tileTypes;
        this.services = new ArrayList<>();
        this.displays = new ArrayList<>();
    }

    public String getFloorName() {
        return floorName;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public String[] getNamesOfServices() {
        return namesOfServices;
    }

    public String[] getNamesOfDisplays() {
        return namesOfDisplays;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public ArrayList<Display> getDisplays() {
        return displays;
    }

    public void generateFloorTiles(int rowCnt, int colCnt) {
        String displayName = null;
        int tableCount = 0;
        int chilledCounterCount = 0;
        int refrigeratorCount = 0;
        int shelfCount = 0;
        int currentCount = 0;
        int sectionCount = 0;

        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                if (j > 0 && j < 3)
                    sectionCount = 1;
                if (j > 3 && j < 6)
                    sectionCount = 2;
                if (j > 6 && j < 9)
                    sectionCount = 3;
                if (j > 10 && j < 13)
                    sectionCount = 4;
                if (j > 13 && j < 16)
                    sectionCount = 5;
                if (j > 16)
                    sectionCount = 6;
                    
                char currentType = tileTypes[i][j];
                Tile tile = new Tile(currentType);
                tiles[i][j] = tile;

                if (currentType == 'B' || currentType == 'C' || currentType == 'D' || currentType == 'E' ||
                currentType == 'F' || currentType == 'G' || currentType == 'H') {
                    Service service = new Service(tile.getTileType(), tile);
                    services.add(service);
                }

                switch (currentType) {
                    case 'B' -> tile.setText(" ^ ");
                    case 'C' -> tile.setText("===");
                    case 'D' -> tile.setText("\\_/");
                    case 'E' -> tile.setText(" i ");
                    case 'F' -> tile.setText("///");
                    case 'G' -> tile.setText("$$$");
                    case 'H' -> tile.setText(" ⌄ ");
                    case 'I' -> {
                        tile.setText(" - ");
                        displayName = "Table";
                        tableCount++;
                        currentCount = tableCount;
                    }
                    case 'J' -> {
                        tile.setText("-+-");
                        displayName = "Chilled Counter";
                        chilledCounterCount++;
                        currentCount = chilledCounterCount;
                    }
                    case 'K' -> {
                        tile.setText("|-|");
                        displayName = "Refrigerator";
                        refrigeratorCount++;
                        currentCount = refrigeratorCount;
                    }
                    case 'L' -> {
                        tile.setText("-|-");
                        displayName = "Shelf";
                        shelfCount++;
                        currentCount = shelfCount;
                    }
                }

                if (currentType == 'I' || currentType == 'J' || currentType == 'K' || currentType == 'L') {
                    String address = floorName + ", " + "Section " + sectionCount + ", " + displayName + " " + currentCount;
                    Display display = new Display(tile.getTileType(), tile, address);
                    displays.add(display);
                }
                
                
            }
        }
    }

    public void displayFloorTiles(int rowCnt, int colCnt) {
        System.out.println();
        for (int i = 0; i < rowCnt; i++) {
            System.out.println();
            for (int j = 0; j < colCnt; j++) {
                Tile currentTile = tiles[i][j];
                if (currentTile.getCharType() != 'M')
                    System.out.print(FontColor.apply(currentTile.getText(), currentTile.getFGColor(), currentTile.getBGColor()));
                else
                    System.out.print(FontColor.apply(currentTile.getText(), currentTile.getFGColor(), currentTile.getBGColor()));
            }
        }

    }

    public void generateProducts() {
        for (Display display : displays) {
            Random random = new Random();
            int currentCapacity = random.nextInt(display.getProductCapacity() + 1);

            if (currentCapacity != 0) {
                String randomName = null;
                int randomNameIndex;
                switch (display.getType()) {
                    case "Table" -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForTable().length);
                        randomName = display.getNamesOfProductsForTable()[randomNameIndex].toUpperCase();
                    }
                    case "Chilled Counter" -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForChilledCounter().length);
                        randomName = display.getNamesOfProductsForChilledCounter()[randomNameIndex].toUpperCase();
                    }

                    case "Refrigerator" -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForRefrigerator().length);
                        randomName = display.getNamesOfProductsForRefrigerator()[randomNameIndex].toUpperCase();
                    }

                    case "Shelf" -> {
                        randomNameIndex = random.nextInt(display.getNamesOfProductsForShelf().length);
                        randomName = display.getNamesOfProductsForShelf()[randomNameIndex].toUpperCase();
                    }
                }

                if (randomName != null) {
                    ProductType productType = ProductType.valueOf(randomName);
                    Brand brand = Brand.valueOf(randomName);
                    for (int i = 0; i < currentCapacity; i++) {
                        double randomPrice = MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble();
                        randomPrice = Math.round(randomPrice * MIN_PRICE) / MIN_PRICE;
                        String currentSerialNumber = "" + randomName.charAt(0) + randomName.charAt(1) + randomName.charAt(2) + String.format("%05d", count);
                        count++;
                        int randomProductTypeIndex = random.nextInt(productType.getProductTypes().size());
                        int randomBrandIndex = random.nextInt(brand.getBrands().size());
                        String randomProductType = productType.getProductTypes().get(randomProductTypeIndex);
                        String randomBrand = brand.getBrands().get(randomBrandIndex);
                        boolean isBeverage = false;
                        boolean isConsumable = false;
                        switch (randomName) {
                            case "FRUIT", "VEGETABLE", "BREAD", "EGGS", "MILK", "FROZEN_FOOD",
                                "CHEESE", "CHICKEN", "BEEF", "SEAFOOD", "CEREAL", "NOODLES",
                                "SNACKS", "CANNED_GOODS", "CONDIMENTS" -> isConsumable = true;
                            case "SOFT_DRINK", "JUICE", "ALCOHOL" -> {
                                isBeverage = true;
                            }

                        }

                        Product product = new Product(currentSerialNumber, randomName, randomPrice, randomProductType, randomBrand, isBeverage, isConsumable);
                        display.addProduct(product);
                    }

                }

            }

        }

    }

}
