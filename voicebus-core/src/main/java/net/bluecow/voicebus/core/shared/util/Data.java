package net.bluecow.voicebus.core.shared.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Data {

    @SafeVarargs
    public static <T> Set<T> setOf(T... ts) {
        return new HashSet<>(listOf(ts));
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... ts) {
        return Arrays.asList(ts);
    }
}
