package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class BodyComponent extends AbstractMainComponent {

    @Override
    public Integer getX() {
        return 116;
    }

    @Override
    public Integer getY() {
        return 45;
    }
}
