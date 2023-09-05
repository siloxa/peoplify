package io.github.shuoros.peoplify.service;

import io.github.shuoros.peoplify.model.Name;
import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.model.enumeration.Language;
import io.github.shuoros.peoplify.model.enumeration.NameType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class NameGeneratorService {

    private static final Random RANDOM = new Random();

    public Map<NameType, Name> generateName(final Language language, final Gender gender) {
        final Language selectedLanguage = selectLanguage(language);
        final Gender selectedGender = selectGender(gender);

        return generateNameInLanguageAndGender(selectedLanguage, selectedGender);
    }

    private Map<NameType, Name> generateNameInLanguageAndGender(final Language language, final Gender gender) {
        final List<Name> firstNames = NamesProvider.firstnames.get(language).get(gender);
        final List<Name> lastNames = NamesProvider.lastnames.get(language);

        return buildResultMap(firstNames, lastNames);
    }

    private Map<NameType, Name> buildResultMap(final List<Name> firstNames, final List<Name> lastNames) {
        return Map.ofEntries(
                Map.entry(NameType.FIRST_NAME, getRandomName(firstNames)),
                Map.entry(NameType.LAST_NAME, getRandomName(lastNames))
        );
    }

    private Name getRandomName(final List<Name> names) {
        return names.get(RANDOM.nextInt(names.size()));
    }

    private Gender selectGender(final Gender gender) {
        return gender != null ? gender : getRandomGender();
    }

    private Language selectLanguage(final Language language) {
        return language != null ? language : getRandomLanguage();
    }

    private Gender getRandomGender() {
        return Gender.values()[RANDOM.nextInt(Gender.values().length)];
    }

    private Language getRandomLanguage() {
        return Language.values()[RANDOM.nextInt(Language.values().length)];
    }
}
