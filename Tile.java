public class Tile {
    private final int row;
    private final int col;
    private char type;
    private final boolean walkable;
    private FontColor color;
    private String text;

    public Tile(int row, int col, boolean walkable, char type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.walkable = walkable;
        switch(type) {
            case '1' -> this.color = FontColor.BG_BLACK;
            case '2' -> this.color = FontColor.BG_BRIGHT_BLACK;
            case '3' -> this.color = FontColor.BG_GREEN;
            case '4' -> this.color = FontColor.BG_CYAN;
            case '5' -> this.color = FontColor.BG_BLUE;
            case '6' -> this.color = FontColor.BG_YELLOW;
            case '7' -> this.color = FontColor.BG_WHITE;
        }
        this.text = "   ";
    }

    public FontColor getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        text = newText;
    }

    public char getType() {
        return type;
    }

    public void setType(char newType) {
        type = newType;
    }

}
