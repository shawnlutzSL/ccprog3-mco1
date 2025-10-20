public class DisplayAddress {
    private final String floor;
    private final String grouping;
    private final int number;

    public DisplayAddress(String floor, String grouping, int number) {
        this.floor = floor;
        this.grouping = grouping;
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public String getGrouping() {
        return grouping;
    }

    public int getNumber() {
        return number;
    }
    
}
