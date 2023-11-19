package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.BodyColor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BodyColorConverter implements Converter<String, BodyColor> {

    @Override
    public BodyColor convert(String source) {
        return BodyColor.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
