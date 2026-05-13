package ru.otus.danilchenko.lib.v1;

import ru.otus.danilchenko.lib.api.LibUtil;

public class LibV1 {
    public static String helloV1() {
        return "Hello from lib v1! Api says: " + LibUtil.hello();
    }
}
