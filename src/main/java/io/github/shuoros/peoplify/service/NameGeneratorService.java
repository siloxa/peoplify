package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.Name;
import io.github.shuoros.peoplify.model.enumeration.Language;
import io.github.shuoros.peoplify.model.enumeration.NameType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class NameGeneratorService {

    // TODO: Generate name base on given gender

    private static final Random RANDOM = new Random();

    public Map<NameType, Name> generateName(final Language language) {
        return language != null ? generateNameInLanguage(language) : generateNameInRandomLanguage();
    }

    public Map<NameType, Name> generateNameInLanguage(final Language language) {
        final Map<NameType, List<Name>> namesInLanguage = NamesProvider.names.get(language);
        return buildResultMap(namesInLanguage);
    }

    public Map<NameType, Name> generateNameInRandomLanguage() {
        final Map<NameType, List<Name>> namesInLanguage = NamesProvider.names.get(
                Language.values()[RANDOM.nextInt(Language.values().length)]
        );
        return buildResultMap(namesInLanguage);
    }

    private Map<NameType, Name> buildResultMap(final Map<NameType, List<Name>> namesInLanguage) {
        return Map.ofEntries(
                Map.entry(NameType.FIRST_NAME, getRandomName(namesInLanguage, NameType.FIRST_NAME)),
                Map.entry(NameType.LAST_NAME, getRandomName(namesInLanguage, NameType.LAST_NAME))
        );
    }

    private Name getRandomName(final Map<NameType, List<Name>> namesInLanguage, final NameType nameType) {
        return namesInLanguage.get(nameType).get(RANDOM.nextInt(namesInLanguage.get(nameType).size()));
    }
}
