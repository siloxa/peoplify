package io.github.shuoros.peoplify.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Language {

    ENGLISH,
    PERSIAN;

    @JsonValue
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
