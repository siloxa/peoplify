package io.github.shuoros.peoplify.controller.dto;

import io.github.shuoros.peoplify.model.enumeration.*;
import lombok.Data;

@Data
public class AvatarRequest {

    private BodyColor bodyColor;
    private FaceExpression faceExpression;
    private HairType hairType;
    private HairColor hairColor;
    private ClothColor clothColor;
}
