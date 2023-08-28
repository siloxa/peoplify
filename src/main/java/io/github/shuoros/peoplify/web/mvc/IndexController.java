package io.github.shuoros.peoplify.web.mvc;

import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.model.enumeration.Language;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("genders", Arrays.stream(Gender.values()).map(Gender::getGender).collect(Collectors.toList()));
        model.addAttribute("languages", Arrays.stream(Language.values()).map(Language::getLanguage).collect(Collectors.toList()));
        model.addAttribute("firstname", "Shayan");
        model.addAttribute("lastname", "Mahjoub");
        return "index";
    }
}
