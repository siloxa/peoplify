package io.github.shuoros.peoplify.model;

import io.github.shuoros.peoplify.model.enumeration.AccessoryColor;
import io.github.shuoros.peoplify.model.enumeration.HairColor;
import lombok.experimental.SuperBuilder;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

@SuperBuilder
public class AccessoryComponent extends OtherComponent {

    private Map<AccessoryColor, BufferedImage> colors;

    @Override
    public BufferedImage getImage() {
        if (colors.isEmpty()) {
            return super.getImage();
        }

        final Random random = new Random();
        return colors.get(
                AccessoryColor.values()[random.nextInt(AccessoryColor.values().length)]
        );
    }

    public BufferedImage getImage(final AccessoryColor accessoryColor) {
        if (colors.isEmpty()) {
            return super.getImage();
        }

        return colors.get(accessoryColor);
    }
}
