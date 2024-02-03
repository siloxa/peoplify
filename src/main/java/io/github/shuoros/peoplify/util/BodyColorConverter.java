package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.AvatarColor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BodyColorConverter implements Converter<String, AvatarColor> {

    @Override
    public AvatarColor convert(String source) {
        return AvatarColor.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
