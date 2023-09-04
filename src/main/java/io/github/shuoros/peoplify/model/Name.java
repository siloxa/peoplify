package io.github.shuoros.peoplify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.model.enumeration.Language;
import io.github.shuoros.peoplify.model.enumeration.NameType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {

    private String name;

    private Gender gender;

    @JsonIgnore
    private NameType nameType;

    private Language language;
}
