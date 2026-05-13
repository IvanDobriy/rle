package ru.otus.danilchenko.lib.api.rle;

import java.nio.file.Path;

public interface IRleDecompressor {
    void decompress(Path compressed, Path decompressed);
}
