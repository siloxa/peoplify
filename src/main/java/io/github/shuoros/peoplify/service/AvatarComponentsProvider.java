package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.enumeration.BodyColor;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AvatarComponentsProvider {

    protected static final Map<BodyColor, BufferedImage> body;

    static {
        body = Map.of(
                BodyColor.YELLOW, loadComponent("body-yellow"),
                BodyColor.BLACK, loadComponent("body-black"),
                BodyColor.BROWN, loadComponent("body-brown"),
                BodyColor.NUDE, loadComponent("body-nude"),
                BodyColor.PINK, loadComponent("body-pink"),
                BodyColor.WHITE, loadComponent("body-white")
        );
    }

    private static BufferedImage loadComponent(String name) {
        try {
            return ImageIO.read(ResourceUtils.getFile("classpath:static/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
