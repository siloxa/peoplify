package io.github.shuoros.peoplify.model.enumeration;

import java.awt.*;

public enum BackgroundColor {

    CYAN(new Color(191, 241, 255)),
    LAVENDER(new Color(216, 201, 255)),
    YELLOW(new Color(249, 255, 180)),
    PINK(new Color(255, 169, 176)),
    BROWN(new Color(199, 178, 153)),
    GREEN(new Color(180, 255, 216)),
    LIGHT_YELLOW(new Color(255, 255, 226)),
    ORANGE(new Color(255, 191, 144)),
    BLUE(new Color(148, 184, 255)),
    AQUAMARINE(new Color(180, 255, 241)),
    PURPLE(new Color(180, 159, 241)),
    SALMON(new Color(255, 148, 138)),
    BLUE_GREEN(new Color(71, 181, 148)),
    MINT_GREEN(new Color(191, 255, 203));

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
