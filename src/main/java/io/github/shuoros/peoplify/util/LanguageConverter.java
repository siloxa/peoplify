package io.github.shuoros.peoplify.util;

import io.github.shuoros.peoplify.model.enumeration.HairType;
import io.github.shuoros.peoplify.model.enumeration.Language;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LanguageConverter implements Converter<String, Language> {

    @Override
    public Language convert(String source) {
        return Language.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
