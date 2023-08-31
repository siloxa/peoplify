package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.FaceType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FaceExpressionConverter implements Converter<String, FaceType> {

    @Override
    public FaceType convert(String source) {
        return FaceType.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
