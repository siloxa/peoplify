package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.enumeration.BodyColor;
import io.github.shuoros.peoplify.model.enumeration.ClothColor;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class AvatarComponentsProvider {

    protected static final Map<BodyColor, BufferedImage> body;
    protected static final Map<ClothColor, BufferedImage> cloth;


    static {
        body = Map.of(
                BodyColor.YELLOW, loadComponent("body-yellow"),
                BodyColor.BLACK, loadComponent("body-black"),
                BodyColor.BROWN, loadComponent("body-brown"),
                BodyColor.NUDE, loadComponent("body-nude"),
                BodyColor.PINK, loadComponent("body-pink"),
                BodyColor.WHITE, loadComponent("body-white")
        );
        cloth = Map.of(
                ClothColor.BLACK, loadComponent("cloth-black"),
                ClothColor.BLUE, loadComponent("cloth-blue"),
                ClothColor.GRAY, loadComponent("cloth-gray"),
                ClothColor.GREEN, loadComponent("cloth-green"),
                ClothColor.ORANGE, loadComponent("cloth-orange"),
                ClothColor.PINK, loadComponent("cloth-pink"),
                ClothColor.PURPLE, loadComponent("cloth-purple"),
                ClothColor.RED, loadComponent("cloth-red"),
                ClothColor.WHITE, loadComponent("cloth-white"),
                ClothColor.YELLOW, loadComponent("cloth-yellow")
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
