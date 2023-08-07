package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.enumeration.DefaultColor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Service
public class AvatarGeneratorService {

    protected static final int CANVAS_SIZE = 32;

    public void generateAvatar(OutputStream outputStream) throws IOException {
        final BufferedImage canvas = setUpCanvas();
        writeToOutputStream(canvas, outputStream);
    }

    private BufferedImage setUpCanvas() {
        final BufferedImage canvas = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_INT_RGB);
        final Color backgroundColor = resolveRandomColor();
        for (int i = 0; i < CANVAS_SIZE; i++) {
            for (int j = 0; j < CANVAS_SIZE; j++) {
                canvas.setRGB(i, j, backgroundColor.getRGB());
            }
        }
        return canvas;
    }

    private Color resolveRandomColor() {
        final Random random = new Random();
        return DefaultColor.values()[random.nextInt(DefaultColor.values().length)].getColor();
    }

    private void writeToOutputStream(BufferedImage canvas, OutputStream outputStream) throws IOException {
        ImageIO.write(canvas, "jpg", outputStream);
    }
}
