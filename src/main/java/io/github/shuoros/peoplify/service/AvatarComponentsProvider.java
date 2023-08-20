package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.*;
import io.github.shuoros.peoplify.model.enumeration.BodyColor;
import io.github.shuoros.peoplify.model.enumeration.ClothColor;
import io.github.shuoros.peoplify.model.enumeration.FaceExpression;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AvatarComponentsProvider {

    protected static final Map<BodyColor, BodyComponent> body;
    protected static final Map<FaceExpression, FaceComponent> face;
    protected static final Map<ClothColor, ClothComponent> cloth;
    protected static final List<OtherComponent> glasses;
    protected static OtherComponent mole;
    protected static OtherComponent headband;
    protected static OtherComponent scar;


    static {
        body = Map.of(
                BodyColor.YELLOW, buildBodyComponent("body-yellow"),
                BodyColor.BLACK, buildBodyComponent("body-black"),
                BodyColor.BROWN, buildBodyComponent("body-brown"),
                BodyColor.NUDE, buildBodyComponent("body-nude"),
                BodyColor.PINK, buildBodyComponent("body-pink"),
                BodyColor.WHITE, buildBodyComponent("body-white")
        );
        face = Map.of(
                FaceExpression.HAPPY, buildFaceComponent("face-happy")
        );
        cloth = Map.of(
                ClothColor.BLACK, buildClothComponent("cloth-black"),
                ClothColor.BLUE, buildClothComponent("cloth-blue"),
                ClothColor.GRAY, buildClothComponent("cloth-gray"),
                ClothColor.GREEN, buildClothComponent("cloth-green"),
                ClothColor.ORANGE, buildClothComponent("cloth-orange"),
                ClothColor.PINK, buildClothComponent("cloth-pink"),
                ClothColor.PURPLE, buildClothComponent("cloth-purple"),
                ClothColor.RED, buildClothComponent("cloth-red"),
                ClothColor.WHITE, buildClothComponent("cloth-white"),
                ClothColor.YELLOW, buildClothComponent("cloth-yellow")
        );
        glasses = List.of(
                buildOtherComponent("glasses-0", 170, 166),
                buildOtherComponent("glasses-1", 152, 154)
        );
        mole = buildOtherComponent("mole", 325, 270);
        headband = buildOtherComponent("headband", 176, 110);
        scar = buildOtherComponent("scar", 240, 140);
    }

    private static BodyComponent buildBodyComponent(String name) {
        return BodyComponent.builder()
                .image(loadImage(name))
                .build();
    }

    private static FaceComponent buildFaceComponent(String name) {
        return FaceComponent.builder()
                .image(loadImage(name))
                .build();
    }

    private static ClothComponent buildClothComponent(String name) {
        return ClothComponent.builder()
                .image(loadImage(name))
                .build();
    }

    private static OtherComponent buildOtherComponent(String name, Integer x, Integer y) {
        return OtherComponent.builder()
                .image(loadImage(name))
                .x(x)
                .y(y)
                .build();
    }

    private static BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(ResourceUtils.getFile("classpath:static/" + name + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
