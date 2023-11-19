package io.github.shuoros.peoplify.web.controller;

import io.github.shuoros.peoplify.model.Name;
import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.model.enumeration.Language;
import io.github.shuoros.peoplify.model.enumeration.NameType;
import io.github.shuoros.peoplify.service.AvatarGeneratorService;
import io.github.shuoros.peoplify.service.NameGeneratorService;
import io.github.shuoros.peoplify.web.controller.dto.AvatarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Map;

@RestController
@RequestMapping("/api/generate")
public class GeneratorController {

    @Autowired
    private AvatarGeneratorService avatarGeneratorService;

    @Autowired
    private NameGeneratorService nameGeneratorService;

    @GetMapping(path = "/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<StreamingResponseBody> generateAvatar(AvatarRequest avatarRequest) {
        return ResponseEntity
                .ok()
                .body(outputStream -> avatarGeneratorService.generateAvatar(avatarRequest, outputStream));
    }

    @GetMapping(path = "/name")
    public Map<NameType, Name> generateName(
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) Gender gender
    ) {
        return nameGeneratorService.generateName(language, gender);
    }
}
