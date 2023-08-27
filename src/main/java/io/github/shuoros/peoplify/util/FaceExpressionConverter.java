package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.FaceExpression;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FaceExpressionConverter implements Converter<String, FaceExpression> {

    @Override
    public FaceExpression convert(String source) {
        return FaceExpression.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
