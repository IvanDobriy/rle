package ru.otus.danilchenko.lib.v1.array;

public class ArrayUtils {
    public static <T> T[] createArray(int size) {
        return (T[]) (new Object[size]);
    }
}
