package ru.otus.danilchenko.lib.v1.hash;

public interface IHasher<K> {
    long execute(K key);
}
