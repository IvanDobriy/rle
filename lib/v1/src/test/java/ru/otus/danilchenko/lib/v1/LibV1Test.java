package ru.otus.danilchenko.lib.v1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibV1Test {
    @Test
    void helloV1ContainsApiMessage() {
        String result = LibV1.helloV1();
        assertTrue(result.contains("Hello from lib v1!"));
        assertTrue(result.contains("Api says:"));
    }
}
