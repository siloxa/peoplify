package io.github.shuoros.peoplify.model.enumeration;

import lombok.Getter;

@Getter
public enum HairType {

    SEMI_BALD(Gender.MALE),
    LONG_AFRO(Gender.FEMALE),
    SHORT_AFRO(Gender.MALE),
    PONY_TAIL(Gender.FEMALE),
    CENTER_PART(Gender.MALE),
    SIDE_PART(Gender.MALE),
    CORNROWS(Gender.MALE);

    final Gender gender;

    HairType(Gender gender) {
        this.gender = gender;
    }
}
