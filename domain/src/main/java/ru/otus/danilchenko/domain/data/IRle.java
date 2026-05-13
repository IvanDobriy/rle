package ru.otus.danilchenko.domain.data;

import java.nio.file.Path;

public interface IRle {
    void compress(Path from, Path to);
    void decompress(Path from, Path to);
}
