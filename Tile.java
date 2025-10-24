public class Tile {
    private char charType;
    private String tileType;
    private FontColor FGColor;
    private FontColor BGColor;

    private String text;
    private boolean walkable;

    public Tile(char charType) {
        this.charType = charType;
        this.tileType = " ";
        this.text = "   ";
        this.walkable = true;
        switch(charType) {
            case 'A' -> {
                this.BGColor = FontColor.BG_BLACK;
                this.FGColor = FontColor.DEFAULT;
                this.tileType = "Wall";
                this.walkable = false;
            }
            case 'B' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Entrance";
            }
            case 'C' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Cart Station";
            }
            case 'D' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Basket Station";
            }
            case 'E' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Product Search";
            }
            case 'F' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Stairs";
            }
            case 'G' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Checkout Counter";
            }
            case 'H' -> {
                this.FGColor = FontColor.DEFAULT;
                this.BGColor = FontColor.BG_BRIGHT_BLACK;
                this.tileType = "Exit";
            }
            case 'I' -> {
                this.FGColor = FontColor.BLACK;
                this.BGColor = FontColor.BG_GREEN;
                this.tileType = "Table";
                this.walkable = false;
            }
            case 'J' -> {
                this.FGColor = FontColor.BLACK;
                this.BGColor = FontColor.BG_CYAN;
                this.tileType = "Chilled Counter";
                this.walkable = false;
            }
            case 'K' -> {
                this.FGColor = FontColor.BLACK;
                this.BGColor = FontColor.BG_BLUE;
                this.tileType = "Refrigerator";
                this.walkable = false;
            }
            case 'L' -> {
                this.FGColor = FontColor.BLACK;
                this.BGColor = FontColor.BG_YELLOW;
                this.tileType = "Shelf";
                this.walkable = false;
            }
            case 'M' -> {
                this.FGColor = FontColor.BLACK;
                this.BGColor = FontColor.BG_WHITE;
                this.tileType = "Empty";
            }
        }
        
    }

    public FontColor getBGColor() {
        return BGColor;
    }

    public FontColor getFGColor() {
        return FGColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        text = newText;
    }

    public char getCharType() {
        return charType;
    }

    public String getTileType() {
        return tileType;
    }

    public boolean IsWalkable() {
        return walkable;
    }

}
