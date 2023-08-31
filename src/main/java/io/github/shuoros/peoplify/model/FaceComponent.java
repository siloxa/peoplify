package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class FaceComponent extends AbstractComponent {

    @Override
    public Integer getX() {
        return 226;
    }

    @Override
    public Integer getY() {
        return 190;
    }
}
