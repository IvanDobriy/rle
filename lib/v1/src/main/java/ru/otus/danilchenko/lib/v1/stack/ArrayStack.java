package ru.otus.danilchenko.lib.v1.stack;

import ru.otus.danilchenko.lib.api.array.IArray;
import ru.otus.danilchenko.lib.api.stack.IStack;

import java.util.Objects;

public class ArrayStack<T> implements IStack<T> {
    private IArray<T> array;
    private int index;

    public ArrayStack(IArray<T> array) {
        Objects.requireNonNull(array);
        this.array = array;
        index = -1;
    }

    @Override
    public void push(T el) {
        index += 1;
        if (array.size() <= index) {
            array.add(index, el);
            return;
        }
        array.set(index, el);
    }

    @Override
    public T pop() {
        if (index < 0) {
            return null;
        }
        return array.get(index--);
    }

    @Override
    public int size() {
        return index + 1;
    }
}

