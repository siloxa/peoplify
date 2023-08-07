package io.github.shuoros.peoplify.model.enumeration;

import java.awt.*;

public enum DefaultColor {
    RED("red", new Color(190, 30, 45)),
    BLUE("blue", new Color(28, 117, 188)),
    GREEN("green", new Color(120, 193, 67)),
    YELLOW("yellow", new Color(231, 209, 35)),
    ORANGE("orange", new Color(245, 119, 31)),
    PURPLE("purple", new Color(127, 63, 152)),
    PINK("pink", new Color(255, 17, 125)),
    BROWN("brown", new Color(117, 76, 41)),
    INDIGO("indigo", new Color(38, 34, 98)),
    GRAY("gray", new Color(115, 115, 115)),
    LAVENDER("lavender", new Color(199, 153, 198)),
    CYAN("cyan", new Color(111, 204, 221)),
    BLACK("black", new Color(0, 0, 0)),
    WHITE("white", new Color(255, 255, 255));

    private String name;
    private Color color;

    DefaultColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public static DefaultColor findByName(String name) {
        for (DefaultColor color : DefaultColor.values()) {
            if (color.name.equalsIgnoreCase(name)) {
                return color;
            }
        }
        return null;
    }

    public Color getColor() {
        return color;
    }
}
