package io.github.shuoros.peoplify.model;

import io.github.shuoros.peoplify.model.enumeration.ClothColor;
import io.github.shuoros.peoplify.model.enumeration.HairColor;
import io.github.shuoros.peoplify.service.AvatarComponentsProvider;
import lombok.experimental.SuperBuilder;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

@SuperBuilder
public class HairComponent extends OtherComponent {

    private Map<HairColor, BufferedImage> hairs;

    @Override
    public BufferedImage getImage() {
        if(hairs.isEmpty()) {
            return super.getImage();
        }

        final Random random = new Random();
        return hairs.get(
                HairColor.values()[random.nextInt(HairColor.values().length)]
        );
    }

}
