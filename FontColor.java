public enum FontColor {
    // The Default CLI Font Color
    DEFAULT("\u001B[0m"),

    // Foreground colors
    BLACK("\u001B[30m"),
    WHITE("\u001B[37m"),

    // Background colors
    BG_BLACK("\u001B[40m"),
    BG_GREEN("\u001B[42m"),
    BG_YELLOW("\u001B[43m"),
    BG_BLUE("\u001B[44m"),
    BG_CYAN("\u001B[46m"),
    BG_WHITE("\u001B[47m"),
    BG_BRIGHT_BLACK("\u001B[100m");

    private final String code;

    FontColor(String code) {
        this.code = code;
    }

    public static String apply(String text, FontColor foregroundColor, FontColor backgroundColor) {
        return foregroundColor.code + backgroundColor.code + text + DEFAULT.code;
    }

}
