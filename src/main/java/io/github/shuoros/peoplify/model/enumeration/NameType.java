package io.github.shuoros.peoplify.model.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NameType {

    @JsonProperty("first_name")
    FIRST_NAME,
    @JsonProperty("last_name")
    LAST_NAME
}
