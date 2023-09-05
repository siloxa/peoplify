package io.github.shuoros.peoplify.model.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NameType {

    @JsonProperty("first-name")
    FIRST_NAME,
    @JsonProperty("last-name")
    LAST_NAME
}
