import java.util.ArrayList;
import java.util.List;

public class Supermarket {
    private final List<Floor> floors;
    private final Shopper shopper;

    public Supermarket(Shopper shopper) {
        this.shopper = shopper;
        this.floors = new ArrayList<>();
        Floor GroundFloor = new Floor(1, TileMap.GroundFloor_GF.getMap());
        Floor SecondFloor = new Floor(2, TileMap.SecondFloor_2F.getMap());
        this.floors.add(GroundFloor);
        this.floors.add(SecondFloor);
    }

    public Shopper getShopper() {
        return shopper;
    }

    public List<Floor> getFloors() {
        return floors;
    }

}
