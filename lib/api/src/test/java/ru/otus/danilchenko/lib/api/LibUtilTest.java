package ru.otus.danilchenko.lib.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LibUtilTest {
    @Test
    void helloReturnsExpected() {
        assertEquals("Hello from lib api!", LibUtil.hello());
    }
}
