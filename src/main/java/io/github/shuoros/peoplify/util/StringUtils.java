package io.github.shuoros.peoplify.util;

public class StringUtils {

    public static String toPascalCase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
