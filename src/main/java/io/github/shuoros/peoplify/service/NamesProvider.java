package io.github.shuoros.peoplify.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import io.github.shuoros.peoplify.model.Name;
import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.model.enumeration.Language;
import io.github.shuoros.peoplify.model.enumeration.NameType;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamesProvider {

    protected final static Map<Language, Map<NameType, List<Name>>> names = new HashMap<>();

    static {
        try (
                final Reader reader = new FileReader(ResourceUtils.getFile("classpath:static/names.csv"));
                final CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()
        ) {
            final List<Name> rawNames = parseCSV(csvReader);
            Arrays.asList(Language.values()).forEach(
                    language ->
                            names.put(
                                    language,
                                    Map.of(
                                            NameType.FIRST_NAME, gatherNamesByLanguageAndType(rawNames, language, NameType.FIRST_NAME),
                                            NameType.LAST_NAME, gatherNamesByLanguageAndType(rawNames, language, NameType.LAST_NAME)
                                    )
                            )
            );
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Name> gatherNamesByLanguageAndType(List<Name> rawNames, Language language, NameType nameType) {
        return rawNames
                .stream()
                .filter(row -> row.getLanguage() == language && row.getNameType() == nameType)
                .toList();
    }

    private static List<Name> parseCSV(CSVReader csvReader) throws IOException, CsvException {
        return csvReader.readAll()
                .stream()
                .map(row ->
                        Name.builder()
                                .name(row[0])
                                .gender("".equals(row[1]) ? null : Gender.valueOf(row[1]))
                                .nameType(NameType.valueOf(row[2]))
                                .language(Language.valueOf(row[3]))
                                .build()
                )
                .toList();
    }
}
