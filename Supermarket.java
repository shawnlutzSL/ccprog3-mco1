import java.util.ArrayList;

public class Supermarket {
    private final ArrayList<Floor> floors;
    private final Shopper shopper;
    private Floor currentFloor;

    public Supermarket(Shopper shopper) {
        this.shopper = shopper;
        this.floors = new ArrayList<>();
        Floor GroundFloor = new Floor("GF", 1, TileMap.GroundFloor_GF.getMap());
        Floor SecondFloor = new Floor("2F", 2, TileMap.SecondFloor_2F.getMap());
        this.floors.add(GroundFloor);
        this.floors.add(SecondFloor);
        this.currentFloor = GroundFloor;
    }

    public Shopper getShopper() {
        return shopper;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setCurrentFloor(Floor newFloor) {
        this.currentFloor = newFloor;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

}
