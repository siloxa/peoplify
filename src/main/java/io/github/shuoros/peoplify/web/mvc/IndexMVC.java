package io.github.shuoros.peoplify.web.mvc;

import io.github.shuoros.peoplify.model.Name;
import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.model.enumeration.Language;
import io.github.shuoros.peoplify.model.enumeration.NameType;
import io.github.shuoros.peoplify.service.NameGeneratorService;
import io.github.shuoros.peoplify.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class IndexMVC {

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @RequestMapping(value = "")
    public String index(Model model) {
        final Map<NameType, Name> name = nameGeneratorService.generateNameInRandomLanguage();

        model.addAttribute("genders", Arrays.stream(Gender.values()).map(gender -> StringUtils.toPascalCase(gender.name())).collect(Collectors.toList()));
        model.addAttribute("languages", Arrays.stream(Language.values()).map(language -> StringUtils.toPascalCase(language.name())).collect(Collectors.toList()));
        model.addAttribute("firstname", name.get(NameType.FIRST_NAME).getName());
        model.addAttribute("lastname", name.get(NameType.LAST_NAME).getName());
        return "index";
    }
}
