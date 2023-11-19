package io.github.shuoros.peoplify.aop;

import io.github.shuoros.peoplify.model.enumeration.Gender;
import io.github.shuoros.peoplify.web.controller.dto.AvatarRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class GeneratorControllerAspect {

    private static final Random RANDOM = new Random();

    @Pointcut("execution(* io.github.shuoros.peoplify.web.controller.GeneratorController.generateAvatar(*))")
    public void aroundAvatarGenerator() {}

    @Around("aroundAvatarGenerator()")
    public Object handleAvatarGenerator(final ProceedingJoinPoint joinPoint) throws Throwable {
        final AvatarRequest avatarRequest = (AvatarRequest) joinPoint.getArgs()[0];
        if (avatarRequest.getGender() == null) {
            avatarRequest.setGender(
                    Gender.values()[RANDOM.nextInt(Gender.values().length)]
            );
        }
        return joinPoint.proceed();
    }
}
