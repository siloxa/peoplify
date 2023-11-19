package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BodyComponent extends AbstractComponent {

    @Override
    public Integer getX() {
        return 116;
    }

    @Override
    public Integer getY() {
        return 45;
    }
}
