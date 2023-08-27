package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.controller.dto.AvatarRequest;
import io.github.shuoros.peoplify.model.*;
import io.github.shuoros.peoplify.model.enumeration.*;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Service
public class AvatarGeneratorService {

    private static final int CANVAS_SIZE = 600;
    private static final Random RANDOM = new Random();

    public void generateAvatar(AvatarRequest avatarRequest, OutputStream outputStream) throws IOException {
        final BufferedImage canvas = setUpCanvas();
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();

        renderBody(graphics, avatarRequest);

        renderFace(graphics, avatarRequest);

        renderCloth(graphics, avatarRequest);

        graphics.dispose();

        writeToOutputStream(canvas, outputStream);
    }

    private BufferedImage setUpCanvas() {
        final BufferedImage canvas = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_INT_RGB);
        final Color backgroundColor = resolveRandomBackgroundColor();
        for (int i = 0; i < CANVAS_SIZE; i++) {
            for (int j = 0; j < CANVAS_SIZE; j++) {
                canvas.setRGB(i, j, backgroundColor.getRGB());
            }
        }
        return canvas;
    }

    private void renderBody(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final BodyComponent body = selectBody(avatarRequest);
        graphics.drawImage(body.getImage(), body.getX(), body.getY(), null);
    }

    private void renderFace(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final FaceComponent face = selectFace(avatarRequest);
        graphics.drawImage(face.getImage(), face.getX(), face.getY(), null);
        renderEaring(graphics);
        renderScar(graphics);
//        renderHeadband(graphics);
        renderMole(graphics);
        renderHair(graphics, avatarRequest);
        renderGlasses(graphics);
    }

    private void renderHair(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final HairComponent hair = selectHair(avatarRequest);
        final BufferedImage hairImage = selectHairColor(avatarRequest, hair);
        graphics.drawImage(hairImage, hair.getX(), hair.getY(), null);
    }

    private BufferedImage selectHairColor(final AvatarRequest avatarRequest, final HairComponent hair) {
        return avatarRequest.getHairColor() != null ? hair.getImage(avatarRequest.getHairColor()) : hair.getImage();
    }

    private void renderEaring(final Graphics2D graphics) {
        final OtherComponent glasses = resolveRandomEaring();
        graphics.drawImage(glasses.getImage(), glasses.getX(), glasses.getY(), null);
    }

    private void renderScar(final Graphics2D graphics) {
        if (wightedRandom(25)) {
            graphics.drawImage(
                    AvatarComponentsProvider.scar.getImage(),
                    AvatarComponentsProvider.scar.getX(),
                    AvatarComponentsProvider.scar.getY(),
                    null
            );
        }
    }

    private void renderHeadband(final Graphics2D graphics) {
        if (wightedRandom(25)) {
            graphics.drawImage(
                    AvatarComponentsProvider.headband.getImage(),
                    AvatarComponentsProvider.headband.getX(),
                    AvatarComponentsProvider.headband.getY(),
                    null
            );
        }
    }

    private void renderMole(final Graphics2D graphics) {
        if (wightedRandom(25)) {
            graphics.drawImage(
                    AvatarComponentsProvider.mole.getImage(),
                    AvatarComponentsProvider.mole.getX(),
                    AvatarComponentsProvider.mole.getY(),
                    null
            );
        }
    }

    private void renderGlasses(final Graphics2D graphics) {
        if (wightedRandom(25)) {
            final OtherComponent glasses = resolveRandomGlasses();
            graphics.drawImage(glasses.getImage(), glasses.getX(), glasses.getY(), null);
        }
    }

    private void renderCloth(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final ClothComponent cloth = selectCloth(avatarRequest);
        graphics.drawImage(cloth.getImage(), cloth.getX(), cloth.getY(), null);
    }

    private Color resolveRandomBackgroundColor() {
        return BackgroundColor.values()[RANDOM.nextInt(BackgroundColor.values().length)].getColor();
    }

    private BodyComponent selectBody(final AvatarRequest avatarRequest) {
        return avatarRequest.getBodyColor() != null ? resolveBody(avatarRequest.getBodyColor()) : resolveRandomBody();
    }

    private BodyComponent resolveBody(final BodyColor bodyColor) {
        return AvatarComponentsProvider.body.get(bodyColor);
    }

    private BodyComponent resolveRandomBody() {
        return AvatarComponentsProvider.body.get(
                BodyColor.values()[RANDOM.nextInt(BodyColor.values().length)]
        );
    }

    private FaceComponent selectFace(AvatarRequest avatarRequest) {
        return avatarRequest.getFaceExpression() != null ? resolveFace(avatarRequest.getFaceExpression()) : resolveRandomFace();
    }

    private FaceComponent resolveFace(final FaceExpression faceExpression) {
        return AvatarComponentsProvider.face.get(faceExpression);
    }

    private FaceComponent resolveRandomFace() {
        return AvatarComponentsProvider.face.get(
                FaceExpression.values()[RANDOM.nextInt(FaceExpression.values().length)]
        );
    }

    private HairComponent selectHair(final AvatarRequest avatarRequest) {
        return avatarRequest.getHairType() != null ? resolveHair(avatarRequest.getHairType()) : resolveRandomHair();
    }

    private HairComponent resolveHair(final HairType hairType) {
        return AvatarComponentsProvider.hair.get(hairType);
    }

    private HairComponent resolveRandomHair() {
        return AvatarComponentsProvider.hair.get(
                HairType.values()[RANDOM.nextInt(HairType.values().length)]
        );
    }

    private OtherComponent resolveRandomEaring() {
        return AvatarComponentsProvider.earing.get(
                RANDOM.nextInt(AvatarComponentsProvider.earing.size())
        );
    }

    private OtherComponent resolveRandomGlasses() {
        return AvatarComponentsProvider.glasses.get(
                RANDOM.nextInt(AvatarComponentsProvider.glasses.size())
        );
    }

    private ClothComponent selectCloth(AvatarRequest avatarRequest) {
        return avatarRequest.getClothColor() != null ? resolveCloth(avatarRequest.getClothColor()) : resolveRandomCloth();
    }

    private ClothComponent resolveCloth(final ClothColor clothColor) {
        return AvatarComponentsProvider.cloth.get(clothColor);
    }

    private ClothComponent resolveRandomCloth() {
        return AvatarComponentsProvider.cloth.get(
                ClothColor.values()[RANDOM.nextInt(ClothColor.values().length)]
        );
    }

    private boolean wightedRandom(int chance) {
        return RANDOM.nextFloat(11) <= (chance / 10F);
    }

    private void writeToOutputStream(final BufferedImage canvas, final OutputStream outputStream) throws IOException {
        ImageIO.write(canvas, "png", outputStream);
        outputStream.close();
    }
}
