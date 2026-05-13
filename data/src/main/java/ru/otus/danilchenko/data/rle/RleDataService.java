package ru.otus.danilchenko.data.rle;

import ru.otus.danilchenko.domain.data.IRle;
import ru.otus.danilchenko.lib.api.rle.IRleCompressor;
import ru.otus.danilchenko.lib.api.rle.IRleDecompressor;

import java.nio.file.Path;
import java.util.Objects;

public class RleDataService implements IRle {
    private final IRleCompressor compressor;
    private final IRleDecompressor decompressor;

    public RleDataService(IRleCompressor compressor, IRleDecompressor decompressor) {
        this.compressor = Objects.requireNonNull(compressor);
        this.decompressor = Objects.requireNonNull(decompressor);
    }

    @Override
    public void compress(Path from, Path to) {
        compressor.compress(from, to);
    }

    @Override
    public void decompress(Path from, Path to) {
        decompressor.decompress(from, to);
    }
}
