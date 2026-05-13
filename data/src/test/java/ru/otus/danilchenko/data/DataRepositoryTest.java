package ru.otus.danilchenko.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataRepositoryTest {
    @Test
    void fetchDataReturnsValue() {
        DataRepository repo = new DataRepository();
        assertNotNull(repo.fetchData());
    }
}
