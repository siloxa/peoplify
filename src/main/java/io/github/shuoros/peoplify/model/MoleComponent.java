package io.github.shuoros.peoplify.model;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MoleComponent extends AbstractComponent  {

    @Override
    public Integer getX() {
        return 325;
    }

    @Override
    public Integer getY() {
        return 270;
    }
}
