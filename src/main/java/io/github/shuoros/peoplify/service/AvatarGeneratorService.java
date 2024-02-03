package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.*;
import io.github.shuoros.peoplify.model.enumeration.*;
import io.github.shuoros.peoplify.util.NumberUtils;
import io.github.shuoros.peoplify.web.controller.dto.AvatarRequest;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AvatarGeneratorService {

    // TODO: tattoo must be rendered on left arm
    // TODO: hair 9, 10 for men

    private static final int CANVAS_SIZE = 600;
    private static final Random RANDOM = new Random();

    public void generateAvatar(final AvatarRequest avatarRequest, final OutputStream outputStream) throws IOException {
        final BufferedImage canvas = setUpCanvas();
        final Graphics2D graphics = (Graphics2D) canvas.getGraphics();

        renderAvatar(graphics, avatarRequest);

        if (avatarRequest.getAvatarType() == AvatarType.BODY) {
            renderCloth(graphics, avatarRequest);
        }

        renderFace(graphics, avatarRequest);

        graphics.dispose();

        writeToOutputStream(
                selectCanvasSize(avatarRequest, canvas),
                outputStream
        );
    }

    private BufferedImage setUpCanvas() {
        final BufferedImage canvas = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_INT_RGB);
        final Color backgroundColor = resolveRandomBackgroundColor();
        paintBackground(canvas, backgroundColor);
        return canvas;
    }

    private void paintBackground(final BufferedImage canvas, final Color backgroundColor) {
        for (int i = 0; i < CANVAS_SIZE; i++) {
            for (int j = 0; j < CANVAS_SIZE; j++) {
                canvas.setRGB(i, j, backgroundColor.getRGB());
            }
        }
    }

    private BufferedImage selectCanvasSize(final AvatarRequest avatarRequest, final BufferedImage canvas) {
        return NumberUtils.isNotEmpty(avatarRequest.getSize()) ? resize(canvas, avatarRequest.getSize()) : canvas;
    }

    public BufferedImage resize(final BufferedImage canvas, int newSize) {
        final Image tmp = canvas.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
        final BufferedImage newCanvas = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D g2d = newCanvas.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return newCanvas;
    }

    private void renderAvatar(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        switch (avatarRequest.getAvatarType()) {
            case BODY -> renderBody(graphics, avatarRequest);
            case HEAD -> renderHead(graphics, avatarRequest);
        }
    }

    private void renderBody(Graphics2D graphics, AvatarRequest avatarRequest) {
        final BodyComponent body = selectBody(avatarRequest);
        graphics.drawImage(body.getImage(), body.getX(), body.getY(), null);
        renderTattoo(graphics);
    }

    private void renderTattoo(final Graphics2D graphics) {
        if (wightedRandom(25)) {
            final CustomizableComponent tattoo = AvatarComponentsProvider.tattoo;
            tattoo.setAvatarType(AvatarType.BODY);
            graphics.drawImage(
                    tattoo.getImage(),
                    tattoo.getX(),
                    tattoo.getY(),
                    null
            );
        }
    }

    private void renderHead(Graphics2D graphics, AvatarRequest avatarRequest) {
        final HeadComponent head = selectHead(avatarRequest);
        graphics.drawImage(head.getImage(), head.getX(), head.getY(), null);
    }

    private void renderFace(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final FaceComponent face = selectFace(avatarRequest);
        face.setAvatarType(avatarRequest.getAvatarType());
        renderFacialHair(graphics, avatarRequest);
        graphics.drawImage(face.getImage(), face.getX(), face.getY(), null);
        renderEaring(graphics, avatarRequest);
        renderScar(graphics, avatarRequest);
//        renderHeadband(graphics, avatarRequest);
        renderMole(graphics, avatarRequest);
        renderHair(graphics, avatarRequest);
        renderGlasses(graphics, avatarRequest);
    }

    private void renderFacialHair(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final Optional<BeardType> beardType = renderBeard(graphics, avatarRequest);
        renderMustache(graphics, avatarRequest, beardType);
    }

    private Optional<BeardType> renderBeard(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        if (avatarRequest.getGender() == Gender.MALE && wightedRandom(50)) {
            final Map.Entry<BeardType, HairComponent> beardWithType = selectBeard(avatarRequest);
            final HairComponent beard = beardWithType.getValue();
            final BufferedImage beardImage = selectBeardColor(avatarRequest, beard);
            beard.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(beardImage, beard.getX(), beard.getY(), null);
            return Optional.ofNullable(beardWithType.getKey());
        }
        return Optional.empty();
    }

    private void renderMustache(final Graphics2D graphics, final AvatarRequest avatarRequest, final Optional<BeardType> beardType) {
        if (canHaveMustache(beardType) && avatarRequest.getGender() == Gender.MALE && wightedRandom(50)) {
            final HairComponent mustache = selectMustache(avatarRequest);
            final BufferedImage mustacheImage = selectMustacheColor(avatarRequest, mustache);
            mustache.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(mustacheImage, mustache.getX(), mustache.getY(), null);
        }
    }

    private boolean canHaveMustache(final Optional<BeardType> beardType) {
        return beardType.isEmpty() || (beardType.isPresent() && beardType.get() != BeardType.GARIBALDI && beardType.get() != BeardType.NED_KELLY);
    }

    private void renderHair(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        final HairComponent hair = selectHair(avatarRequest);
        final BufferedImage hairImage = selectHairColor(avatarRequest, hair);
        hair.setAvatarType(avatarRequest.getAvatarType());
        graphics.drawImage(hairImage, hair.getX(), hair.getY(), null);
    }

    private void renderEaring(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        if (wightedRandom(25)) {
            final CustomizableComponent earing = resolveRandomEaring();
            earing.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(earing.getImage(), earing.getX(), earing.getY(), null);
        }
    }

    private void renderScar(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        if (wightedRandom(10)) {
            final CustomizableComponent scar = AvatarComponentsProvider.scar;
            scar.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(
                    scar.getImage(),
                    scar.getX(),
                    scar.getY(),
                    null
            );
        }
    }

    private void renderHeadband(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        if (wightedRandom(25)) {
            final CustomizableComponent headband = AvatarComponentsProvider.headband;
            headband.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(
                    headband.getImage(),
                    headband.getX(),
                    headband.getY(),
                    null
            );
        }
    }

    private void renderMole(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        if (wightedRandom(25)) {
            final CustomizableComponent mole = AvatarComponentsProvider.mole;
            mole.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(
                    mole.getImage(),
                    mole.getX(),
                    mole.getY(),
                    null
            );
        }
    }

    private void renderGlasses(final Graphics2D graphics, final AvatarRequest avatarRequest) {
        if (wightedRandom(25)) {
            final AccessoryComponent glasses = selectGlasses(avatarRequest);
            final BufferedImage glassesImage = selectGlassesColor(avatarRequest, glasses);
            glasses.setAvatarType(avatarRequest.getAvatarType());
            graphics.drawImage(glassesImage, glasses.getX(), glasses.getY(), null);
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
        return avatarRequest.getAvatarColor() != null ? resolveBody(avatarRequest.getAvatarColor()) : resolveRandomBody();
    }

    private BodyComponent resolveBody(final AvatarColor avatarColor) {
        return AvatarComponentsProvider.body.get(avatarColor);
    }

    private BodyComponent resolveRandomBody() {
        return AvatarComponentsProvider.body.get(
                AvatarColor.values()[RANDOM.nextInt(AvatarColor.values().length)]
        );
    }

    private HeadComponent selectHead(final AvatarRequest avatarRequest) {
        return avatarRequest.getAvatarColor() != null ? resolveHead(avatarRequest.getAvatarColor()) : resolveRandomHead();
    }

    private HeadComponent resolveHead(final AvatarColor avatarColor) {
        return AvatarComponentsProvider.head.get(avatarColor);
    }

    private HeadComponent resolveRandomHead() {
        return AvatarComponentsProvider.head.get(
                AvatarColor.values()[RANDOM.nextInt(AvatarColor.values().length)]
        );
    }

    private FaceComponent selectFace(AvatarRequest avatarRequest) {
        return avatarRequest.getFaceType() != null ? resolveFace(avatarRequest.getFaceType()) : resolveRandomFace();
    }

    private FaceComponent resolveFace(final FaceType faceType) {
        return AvatarComponentsProvider.face.get(faceType);
    }

    private FaceComponent resolveRandomFace() {
        return AvatarComponentsProvider.face.get(
                FaceType.values()[RANDOM.nextInt(FaceType.values().length)]
        );
    }

    private BufferedImage selectHairColor(final AvatarRequest avatarRequest, final HairComponent hair) {
        return avatarRequest.getHairColor() != null ? hair.getImage(avatarRequest.getHairColor()) : hair.getImage();
    }

    private HairComponent selectHair(final AvatarRequest avatarRequest) {
        return avatarRequest.getHairType() != null ? resolveHair(avatarRequest.getHairType()) : resolveRandomHair(avatarRequest.getGender());
    }

    private HairComponent resolveHair(final HairType hairType) {
        return AvatarComponentsProvider.hair.get(hairType);
    }

    private HairComponent resolveRandomHair(final Gender gender) {
        final LinkedHashMap<HairType, HairComponent> hairs =
                AvatarComponentsProvider.hair
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().getGender() == gender)
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new
                                )
                        );
        return new ArrayList<>(hairs.values()).get(RANDOM.nextInt(hairs.size()));
    }

    private BufferedImage selectBeardColor(final AvatarRequest avatarRequest, final HairComponent beard) {
        return avatarRequest.getBeardColor() != null ? beard.getImage(avatarRequest.getBeardColor()) : beard.getImage();
    }

    private Map.Entry<BeardType, HairComponent> selectBeard(final AvatarRequest avatarRequest) {
        return avatarRequest.getBeardType() != null ? resolveBeard(avatarRequest.getBeardType()) : resolveRandomBeard();
    }

    private Map.Entry<BeardType, HairComponent> resolveBeard(final BeardType beardType) {
        return Map.entry(beardType, AvatarComponentsProvider.beard.get(beardType));
    }

    private Map.Entry<BeardType, HairComponent> resolveRandomBeard() {
        final BeardType beardType = BeardType.values()[RANDOM.nextInt(BeardType.values().length)];
        return Map.entry(beardType, AvatarComponentsProvider.beard.get(beardType));
    }

    private BufferedImage selectMustacheColor(final AvatarRequest avatarRequest, final HairComponent mustache) {
        return avatarRequest.getMustacheColor() != null ? mustache.getImage(avatarRequest.getMustacheColor()) : mustache.getImage();
    }

    private HairComponent selectMustache(final AvatarRequest avatarRequest) {
        return avatarRequest.getMustacheType() != null ? resolveMustache(avatarRequest.getMustacheType()) : resolveRandomMustache();
    }

    private HairComponent resolveMustache(final MustacheType mustacheType) {
        return AvatarComponentsProvider.mustache.get(mustacheType);
    }

    private HairComponent resolveRandomMustache() {
        return AvatarComponentsProvider.mustache.get(
                MustacheType.values()[RANDOM.nextInt(MustacheType.values().length)]
        );
    }

    private CustomizableComponent resolveRandomEaring() {
        return AvatarComponentsProvider.earing.get(
                RANDOM.nextInt(AvatarComponentsProvider.earing.size())
        );
    }

    private BufferedImage selectGlassesColor(final AvatarRequest avatarRequest, final AccessoryComponent glasses) {
        return avatarRequest.getGlassesColor() != null ? glasses.getImage(avatarRequest.getGlassesColor()) : glasses.getImage();
    }

    private AccessoryComponent selectGlasses(final AvatarRequest avatarRequest) {
        return avatarRequest.getGlassesType() != null ? resolveGlasses(avatarRequest.getGlassesType()) : resolveRandomGlasses();
    }

    private AccessoryComponent resolveGlasses(final GlassesType glassesType) {
        return AvatarComponentsProvider.glasses.get(glassesType);
    }

    private AccessoryComponent resolveRandomGlasses() {
        return AvatarComponentsProvider.glasses.get(
                GlassesType.values()[RANDOM.nextInt(GlassesType.values().length)]
        );
    }

    private ClothComponent selectCloth(AvatarRequest avatarRequest) {
        return avatarRequest.getClothColor() != null ? resolveCloth(avatarRequest.getClothColor()) : resolveRandomCloth();
    }

    private ClothComponent resolveCloth(final AccessoryColor clothColor) {
        return AvatarComponentsProvider.cloth.get(clothColor);
    }

    private ClothComponent resolveRandomCloth() {
        return AvatarComponentsProvider.cloth.get(
                AccessoryColor.values()[RANDOM.nextInt(AccessoryColor.values().length)]
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
