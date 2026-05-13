package ru.otus.danilchenko.lib.v1.hash;

import org.junit.jupiter.api.Test;
import ru.otus.danilchenko.lib.api.array.IArray;

import static org.junit.jupiter.api.Assertions.*;

class OpenAddressHashTableTest {

    @Test
    void insertAndFind() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        table.insert("one", 1);
        assertEquals(1, table.find("one"));
    }

    @Test
    void findNonExistingReturnsNull() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        assertNull(table.find("missing"));
    }

    @Test
    void sizeIncreasesOnInsert() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        assertEquals(0, table.size());
        table.insert("a", 1);
        assertEquals(1, table.size());
        table.insert("b", 2);
        assertEquals(2, table.size());
    }

    @Test
    void removeExistingKey() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        table.insert("a", 1);
        table.remove("a");
        assertNull(table.find("a"));
        assertEquals(0, table.size());
    }

    @Test
    void removeDoesNotAffectOthers() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        table.insert("a", 1);
        table.insert("b", 2);
        table.remove("a");
        assertEquals(2, table.find("b"));
        assertEquals(1, table.size());
    }

    @Test
    void insertWithCollisions() {
        IHasher<String> constantHasher = key -> 5L;
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(constantHasher, 10, 1);
        table.insert("a", 1);
        table.insert("b", 2);
        table.insert("c", 3);
        assertEquals(1, table.find("a"));
        assertEquals(2, table.find("b"));
        assertEquals(3, table.find("c"));
    }

    @Test
    void removeWithCollisions() {
        IHasher<String> constantHasher = key -> 5L;
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(constantHasher, 10, 1);
        table.insert("a", 1);
        table.insert("b", 2);
        table.insert("c", 3);
        table.remove("b");
        assertNull(table.find("b"));
        assertEquals(1, table.find("a"));
        assertEquals(3, table.find("c"));
    }

    @Test
    void rehashTriggeredAutomatically() {
        OpenAddressHashTable<Integer, String> table = new OpenAddressHashTable<>(k -> Integer.hashCode(k), 10, 1);
        for (int i = 0; i < 10; i++) {
            table.insert(i, "value" + i);
        }
        assertEquals(10, table.size());
        for (int i = 0; i < 10; i++) {
            assertEquals("value" + i, table.find(i));
        }
    }

    @Test
    void keysReturnsAllKeys() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        table.insert("a", 1);
        table.insert("b", 2);
        IArray<String> keys = table.keys();
        assertEquals(2, keys.size());
    }

    @Test
    void nullKeyThrowsException() {
        OpenAddressHashTable<String, Integer> table = new OpenAddressHashTable<>(String::hashCode, 10, 1);
        assertThrows(NullPointerException.class, () -> table.insert(null, 1));
        assertThrows(NullPointerException.class, () -> table.find(null));
        assertThrows(NullPointerException.class, () -> table.remove(null));
    }
}
