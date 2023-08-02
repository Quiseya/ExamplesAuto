package ui.util;

import lombok.SneakyThrows;

public class WaitHelper {
    private static final Object lock = new Object();

    @SneakyThrows
    public static void sleep(long milliSeconds) {
        Thread.sleep(milliSeconds);
    }
}
