package ru.otus.danilchenko.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainServiceTest {
    @Test
    void getMessageReturnsValue() {
        DomainService service = new DomainService();
        assertNotNull(service.getMessage());
    }
}
