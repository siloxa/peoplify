package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.AvatarType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AvatarTypeConverter implements Converter<String, AvatarType> {

    @Override
    public AvatarType convert(String source) {
        return AvatarType.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
