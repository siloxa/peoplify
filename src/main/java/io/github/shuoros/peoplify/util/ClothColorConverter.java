package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.AccessoryColor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ClothColorConverter implements Converter<String, AccessoryColor> {

    @Override
    public AccessoryColor convert(String source) {
        return AccessoryColor.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
