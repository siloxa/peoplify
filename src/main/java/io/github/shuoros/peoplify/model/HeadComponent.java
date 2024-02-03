package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class HeadComponent extends AbstractMainComponent {

    @Override
    public Integer getX() {
        return 143;
    }

    @Override
    public Integer getY() {
        return 115;
    }
}
