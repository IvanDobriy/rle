package ru.otus.danilchenko.lib.api.rle;

import java.nio.file.Path;

public interface IRleCompressor {
    void compress(Path initial, Path compressed);

}
