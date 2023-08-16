package io.github.shuoros.peoplify.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SuperBuilder
@Getter
public abstract class AbstractComponent {

    private BufferedImage image;

    public abstract Integer getX();

    public abstract Integer getY();
}
