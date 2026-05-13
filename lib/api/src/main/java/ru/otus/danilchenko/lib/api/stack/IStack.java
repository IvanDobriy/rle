package ru.otus.danilchenko.lib.api.stack;

public interface IStack<T> {
    void push(T el);
    T pop();
    int size();
}
