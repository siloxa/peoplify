package io.github.shuoros.peoplify.service;

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

        renderBody(canvas);

        renderFace(canvas);

        renderCloth(canvas);

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

    private void renderBody(final BufferedImage canvas) {
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();
        final BufferedImage body = resolveRandomBody();
        graphics.drawImage(body, 116, 22, null);
        graphics.dispose();
    }

    private void renderFace(final BufferedImage canvas) {
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();
        final BufferedImage face = resolveRandomFace();
        graphics.drawImage(face, 216, 160, null);
        graphics.dispose();
    }

    private void renderCloth(final BufferedImage canvas) {
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();
        final BufferedImage cloth = resolveRandomCloth();
        graphics.drawImage(cloth, 109, 384, null);
        graphics.dispose();
    }

    private Color resolveRandomBackgroundColor() {
        return BackgroundColor.values()[RANDOM.nextInt(BackgroundColor.values().length)].getColor();
    }

    private BufferedImage resolveRandomBody() {
        return AvatarComponentsProvider.body.get(
                BodyColor.values()[RANDOM.nextInt(BodyColor.values().length)]
        );
    }

    private BufferedImage resolveRandomFace() {
        return AvatarComponentsProvider.face.get(
                FaceExpression.values()[RANDOM.nextInt(FaceExpression.values().length)]
        );
    }

    private BufferedImage resolveRandomCloth() {
        return AvatarComponentsProvider.cloth.get(
                ClothColor.values()[RANDOM.nextInt(ClothColor.values().length)]
        );
    }

    private void writeToOutputStream(final BufferedImage canvas, final OutputStream outputStream) throws IOException {
        ImageIO.write(canvas, "png", outputStream);
        outputStream.close();
    }
}
