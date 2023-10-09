package io.github.shuoros.peoplify.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiMVC {

    @RequestMapping(value = "/api")
    public String api() {
        return "api";
    }
}
