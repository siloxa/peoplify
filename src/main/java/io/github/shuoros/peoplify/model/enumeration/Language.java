package io.github.shuoros.peoplify.model.enumeration;

import lombok.Getter;

@Getter
public enum Language {

    ENGLISH("English"),
    DEUTSCH("Deutsch"),
    PERSIAN("Persian");

    final String language;

    Language(String language) {
        this.language = language;
    }
}
