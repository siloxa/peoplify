package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.enumeration.BackgroundColor;
import io.github.shuoros.peoplify.model.enumeration.BodyColor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Service
public class AvatarGeneratorService {

    protected static final int CANVAS_SIZE = 1024;
    protected static final Random RANDOM = new Random();

    public void generateAvatar(OutputStream outputStream) throws IOException {
        final BufferedImage canvas = setUpCanvas();

        renderBody(canvas);

        writeToOutputStream(canvas, outputStream);
    }

    private void renderBody(BufferedImage canvas) {
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();
        final BufferedImage body = resolveRandomBody();
        graphics.drawImage(body, 20, 50, null);
        graphics.dispose();
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

    private BufferedImage resolveRandomBody() {
        return AvatarComponentsProvider.body.get(
                BodyColor.values()[RANDOM.nextInt(BodyColor.values().length)]
        );
    }

    private Color resolveRandomBackgroundColor() {
        return BackgroundColor.values()[RANDOM.nextInt(BackgroundColor.values().length)].getColor();
    }

    private void writeToOutputStream(BufferedImage canvas, OutputStream outputStream) throws IOException {
        ImageIO.write(canvas, "png", outputStream);
        outputStream.close();
    }
}
