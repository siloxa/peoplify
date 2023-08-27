package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.HairColor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class HairColorConverter implements Converter<String, HairColor> {

    @Override
    public HairColor convert(String source) {
        return HairColor.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
