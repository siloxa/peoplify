package io.github.shuoros.peoplify.web.controller;

import io.github.shuoros.peoplify.service.AvatarGeneratorService;
import io.github.shuoros.peoplify.web.controller.dto.AvatarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/generate")
public class GeneratorController {

    @Autowired
    private AvatarGeneratorService avatarGeneratorService;

    @GetMapping(path = "/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<StreamingResponseBody> generateAvatar(AvatarRequest avatarRequest) {
        return ResponseEntity
                .ok()
                .body(outputStream -> avatarGeneratorService.generateAvatar(avatarRequest, outputStream));
    }
}
