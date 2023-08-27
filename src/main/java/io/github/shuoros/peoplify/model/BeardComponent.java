package io.github.shuoros.peoplify.model;

import io.github.shuoros.peoplify.model.enumeration.HairColor;
import lombok.experimental.SuperBuilder;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

@SuperBuilder
public class BeardComponent extends OtherComponent {

    private Map<HairColor, BufferedImage> beards;

    @Override
    public BufferedImage getImage() {
        if (beards.isEmpty()) {
            return super.getImage();
        }

        final Random random = new Random();
        return beards.get(
                HairColor.values()[random.nextInt(HairColor.values().length)]
        );
    }

    public BufferedImage getImage(final HairColor hairColor) {
        if (beards.isEmpty()) {
            return super.getImage();
        }

        return beards.get(hairColor);
    }
}
