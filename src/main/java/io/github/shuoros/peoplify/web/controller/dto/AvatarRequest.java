package io.github.shuoros.peoplify.web.controller.dto;

import io.github.shuoros.peoplify.model.enumeration.*;
import lombok.Data;

@Data
public class AvatarRequest {

    private Integer size;
    private AvatarType avatarType;
    private Gender gender;
    private AvatarColor avatarColor;
    private FaceType faceType;
    private HairType hairType;
    private HairColor hairColor;
    private BeardType beardType;
    private HairColor beardColor;
    private MustacheType mustacheType;
    private HairColor mustacheColor;
    private AccessoryColor clothColor;
    private GlassesType glassesType;
    private AccessoryColor glassesColor;
}
