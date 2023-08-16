package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.BodyComponent;
import io.github.shuoros.peoplify.model.ClothComponent;
import io.github.shuoros.peoplify.model.FaceComponent;
import io.github.shuoros.peoplify.model.GlassesComponent;
import io.github.shuoros.peoplify.model.enumeration.BackgroundColor;
import io.github.shuoros.peoplify.model.enumeration.BodyColor;
import io.github.shuoros.peoplify.model.enumeration.ClothColor;
import io.github.shuoros.peoplify.model.enumeration.FaceExpression;
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

    public void generateAvatar(OutputStream outputStream) throws IOException {
        final BufferedImage canvas = setUpCanvas();
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();

        renderBody(graphics);

        renderFace(graphics);

        renderCloth(graphics);

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

    private void renderBody(final Graphics2D graphics) {
        final BodyComponent body = resolveRandomBody();
        graphics.drawImage(body.getImage(), body.getX(), body.getY(), null);
    }

    private void renderFace(final Graphics2D graphics) {
        final FaceComponent face = resolveRandomFace();
        graphics.drawImage(face.getImage(), face.getX(), face.getY(), null);
        renderGlasses(graphics);
        renderMole(graphics);
    }

    private void renderGlasses(Graphics2D graphics) {
        if (wightedRandom(25)) {
            final GlassesComponent glasses = resolveRandomGlasses();
            graphics.drawImage(glasses.getImage(), glasses.getX(), glasses.getY(), null);
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

    private void renderCloth(final Graphics2D graphics) {
        final ClothComponent cloth = resolveRandomCloth();
        graphics.drawImage(cloth.getImage(), cloth.getX(), cloth.getY(), null);
    }

    private Color resolveRandomBackgroundColor() {
        return BackgroundColor.values()[RANDOM.nextInt(BackgroundColor.values().length)].getColor();
    }

    private BodyComponent resolveRandomBody() {
        return AvatarComponentsProvider.body.get(
                BodyColor.values()[RANDOM.nextInt(BodyColor.values().length)]
        );
    }

    private FaceComponent resolveRandomFace() {
        return AvatarComponentsProvider.face.get(
                FaceExpression.values()[RANDOM.nextInt(FaceExpression.values().length)]
        );
    }

    private GlassesComponent resolveRandomGlasses() {
        return AvatarComponentsProvider.glasses.get(
                RANDOM.nextInt(AvatarComponentsProvider.glasses.size())
        );
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
