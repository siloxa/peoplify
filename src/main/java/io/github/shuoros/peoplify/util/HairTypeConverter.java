package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.HairType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class HairTypeConverter implements Converter<String, HairType> {

    @Override
    public HairType convert(String source) {
        return HairType.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
