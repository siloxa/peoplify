package io.github.shuoros.peoplify.model.enumeration;

import java.awt.*;

public enum BackgroundColor {
    RED(new Color(190, 30, 45)),
    BLUE(new Color(28, 117, 188)),
    GREEN(new Color(120, 193, 67)),
    YELLOW(new Color(231, 209, 35)),
    ORANGE(new Color(245, 119, 31)),
    PURPLE(new Color(127, 63, 152)),
    PINK(new Color(255, 17, 125)),
    BROWN(new Color(117, 76, 41)),
    INDIGO(new Color(38, 34, 98)),
    GRAY(new Color(115, 115, 115)),
    LAVENDER(new Color(199, 153, 198)),
    CYAN(new Color(111, 204, 221)),
    BLACK(new Color(0, 0, 0)),
    WHITE(new Color(255, 255, 255));

    private Color color;

    BackgroundColor(Color color) {
        this.color = color;
    }

    public static BackgroundColor findByName(String name) {
        for (BackgroundColor color : BackgroundColor.values()) {
            if (color.name().equalsIgnoreCase(name)) {
                return color;
            }
        }
        return null;
    }

    public Color getColor() {
        return color;
    }
}
