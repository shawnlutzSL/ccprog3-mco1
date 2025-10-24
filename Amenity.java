import java.util.Scanner;

public abstract class Amenity {
    protected String type;
    protected Tile location;

    public Amenity(String type, Tile location) {
        this.type = type;
        this.location = location;
    }

    public abstract void interact(Shopper shopper, Scanner scanner, Supermarket market);

    public String getType() {
        return type;
    }

    public Tile getLocation() {
        return location;
    }
}
