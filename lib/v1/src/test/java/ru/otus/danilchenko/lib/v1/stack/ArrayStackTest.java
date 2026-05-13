package ru.otus.danilchenko.lib.v1.stack;

import org.junit.jupiter.api.Test;
import ru.otus.danilchenko.lib.v1.array.SingleArray;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void popFromEmptyStackReturnsNull() {
        ArrayStack<Integer> stack = new ArrayStack<>(new SingleArray<>(10));
        assertNull(stack.pop());
    }

    @Test
    void sizeOfEmptyStackIsZero() {
        ArrayStack<String> stack = new ArrayStack<>(new SingleArray<>(5));
        assertEquals(0, stack.size());
    }

    @Test
    void pushIncreasesSize() {
        ArrayStack<Integer> stack = new ArrayStack<>(new SingleArray<>(3));
        stack.push(10);
        assertEquals(1, stack.size());
        stack.push(20);
        assertEquals(2, stack.size());
    }

    @Test
    void popDecreasesSize() {
        ArrayStack<Integer> stack = new ArrayStack<>(new SingleArray<>(3));
        stack.push(10);
        stack.push(20);
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void pushPopSingleElement() {
        ArrayStack<String> stack = new ArrayStack<>(new SingleArray<>(2));
        stack.push("hello");
        assertEquals("hello", stack.pop());
    }

    @Test
    void pushPopMultipleElementsLifo() {
        ArrayStack<Integer> stack = new ArrayStack<>(new SingleArray<>(5));
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void pushBeyondInitialCapacity() {
        ArrayStack<Integer> stack = new ArrayStack<>(new SingleArray<>(2));
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.size());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
    }

    @Test
    void interleavedPushPop() {
        ArrayStack<String> stack = new ArrayStack<>(new SingleArray<>(3));
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.pop());
        stack.push("c");
        stack.push("d");
        assertEquals("d", stack.pop());
        assertEquals("c", stack.pop());
        assertEquals("a", stack.pop());
        assertNull(stack.pop());
    }
}
