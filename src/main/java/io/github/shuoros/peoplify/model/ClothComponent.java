package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ClothComponent extends AbstractComponent {

    @Override
    public Integer getX() {
        return 109;
    }

    @Override
    public Integer getY() {
        return 407;
    }
}
