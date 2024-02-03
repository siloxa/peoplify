package io.github.shuoros.peoplify.model;

import io.github.shuoros.peoplify.model.enumeration.AvatarType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.awt.image.BufferedImage;

@SuperBuilder
@Getter
public abstract class AbstractTypeBasedComponent implements Component {

    private static final int HEAD_DIFF = -70;

    private BufferedImage image;

    @Setter
    private AvatarType avatarType;

    protected Integer calculateYPosition(final int y) {
        return switch (getAvatarType()) {
            case BODY -> y;
            case HEAD -> y - HEAD_DIFF;
        };
    }
}
