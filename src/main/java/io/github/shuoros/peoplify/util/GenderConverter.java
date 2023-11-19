package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.Gender;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class GenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        return Gender.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
