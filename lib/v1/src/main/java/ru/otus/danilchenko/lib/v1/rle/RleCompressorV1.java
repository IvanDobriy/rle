package ru.otus.danilchenko.lib.v1.rle;

import ru.otus.danilchenko.lib.api.rle.IRleCompressor;
import ru.otus.danilchenko.lib.api.rle.IRleDecompressor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RleCompressorV1 implements IRleCompressor, IRleDecompressor {

    @Override
    public void compress(Path initial, Path compressed) {
        try (InputStream in = Files.newInputStream(initial);
             OutputStream out = Files.newOutputStream(compressed)) {

            int current = in.read();
            if (current == -1) {
                return;
            }

            int count = 1;
            int b;
            while ((b = in.read()) != -1) {
                if (b == current && count < 255) {
                    count++;
                } else {
                    out.write(count);
                    out.write(current);
                    current = b;
                    count = 1;
                }
            }
            out.write(count);
            out.write(current);

        } catch (IOException e) {
            throw new RuntimeException("Compression failed", e);
        }
    }

    @Override
    public void decompress(Path compressed, Path decompressed) {
        try (InputStream in = Files.newInputStream(compressed);
             OutputStream out = Files.newOutputStream(decompressed)) {

            int count;
            while ((count = in.read()) != -1) {
                int value = in.read();
                if (value == -1) {
                    throw new IllegalArgumentException("Invalid RLE data: odd number of bytes");
                }
                for (int i = 0; i < count; i++) {
                    out.write(value);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Decompression failed", e);
        }
    }
}
