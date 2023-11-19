package io.github.shuoros.peoplify.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.awt.image.BufferedImage;

@SuperBuilder
@Getter
public abstract class AbstractComponent {

    private BufferedImage image;

    public abstract Integer getX();

    public abstract Integer getY();
}
