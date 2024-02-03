package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CustomizableComponent extends AbstractTypeBasedComponent {

    private Integer x;

    private Integer y;

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return calculateYPosition(y);
    }
}
