package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.ClothColor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ClothColorConverter implements Converter<String, ClothColor> {

    @Override
    public ClothColor convert(String source) {
        return ClothColor.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
