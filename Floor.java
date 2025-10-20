public class Floor {
    private final int floorNumber;
    private final Tile[][] tiles;
    private final char[][] tileTypes;

    public Floor(int floorNumber, char[][] tileTypes) {
        this.floorNumber = floorNumber;
        this.tiles = new Tile[22][22];
        this.tileTypes = tileTypes;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Tile getTile(int row, int col, int rowCnt, int colCnt) {
        if (row < rowCnt && row > -1 && col < colCnt && col > -1)
            return tiles[row][col];
        
        return null;
    }

    public void generateFloorTiles(int rowCnt, int colCnt) {
        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                char currentType = tileTypes[i][j];
                boolean walkableStatus = false;
                if (currentType == '7')
                    walkableStatus = true;
                Tile tile = new Tile(i, j, walkableStatus, tileTypes[i][j]);
                tiles[i][j] = tile;
            }
        }
    }

    public void displayFloorTiles(int rowCnt, int colCnt) {
        for (int i = 0; i < rowCnt; i++) {
            System.out.println();
            for (int j = 0; j < colCnt; j++) {
                Tile currentTile = tiles[i][j];
                if (currentTile.getType() != '7')
                    System.out.print(FontColor.apply(currentTile.getText(), FontColor.DEFAULT, currentTile.getColor()));
                else
                    System.out.print(FontColor.apply(currentTile.getText(), FontColor.BLACK, currentTile.getColor()));
            }
        }

    }

    public void moveShopper(Shopper shopper) {
        tiles[shopper.getRow()][shopper.getCol()].setText(" S ");
    }

}
