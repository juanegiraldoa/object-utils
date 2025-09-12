package dev.juanes;

import java.util.Arrays;
import java.util.Objects;

public class ObjectUtils {
    public static Object firstNonNull(Object ...objects) {
        return Arrays.stream(objects).filter(Objects::nonNull).findFirst().orElse(null);
    }
}
