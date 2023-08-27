package io.github.shuoros.peoplify.web.controller.dto;

import io.github.shuoros.peoplify.model.enumeration.*;
import lombok.Data;

@Data
public class AvatarRequest {

    private Integer size;
    private BodyColor bodyColor;
    private FaceExpression faceExpression;
    private HairType hairType;
    private HairColor hairColor;
    private ClothColor clothColor;
}
