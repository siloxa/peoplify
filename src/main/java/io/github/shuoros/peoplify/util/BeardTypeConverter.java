package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.BeardType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BeardTypeConverter implements Converter<String, BeardType> {

    @Override
    public BeardType convert(String source) {
        return BeardType.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
