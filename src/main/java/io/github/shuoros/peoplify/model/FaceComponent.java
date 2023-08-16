package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class FaceComponent extends AbstractComponent {

    @Override
    public Integer getX() {
        return 216;
    }

    @Override
    public Integer getY() {
        return 183;
    }
}
