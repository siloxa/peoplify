package io.github.shuoros.peoplify.model.enumeration;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
