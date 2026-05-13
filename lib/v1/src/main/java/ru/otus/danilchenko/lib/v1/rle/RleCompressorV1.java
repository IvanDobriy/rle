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

            byte[] data = in.readAllBytes();
            if (data.length == 0) {
                return;
            }

            int i = 0;
            while (i < data.length) {
                if (i + 1 < data.length && data[i] == data[i + 1]) {
                    // Повторяющаяся последовательность: 1..127
                    int runStart = i;
                    byte value = data[i];
                    while (i < data.length && data[i] == value && (i - runStart) < 127) {
                        i++;
                    }
                    int count = i - runStart;
                    out.write(count);
                    out.write(value);
                } else {
                    // Неповторяющаяся последовательность: -128..-1 (длина 1..128)
                    int runStart = i;
                    while (i < data.length) {
                        if (i + 1 < data.length && data[i] == data[i + 1]) {
                            break;
                        }
                        if ((i - runStart) >= 128) {
                            break;
                        }
                        i++;
                    }
                    int count = i - runStart;
                    out.write(-count);
                    for (int j = runStart; j < i; j++) {
                        out.write(data[j]);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Compression failed", e);
        }
    }

    @Override
    public void decompress(Path compressed, Path decompressed) {
        try (InputStream in = Files.newInputStream(compressed);
             OutputStream out = Files.newOutputStream(decompressed)) {

            int countByte;
            while ((countByte = in.read()) != -1) {
                byte count = (byte) countByte;
                if (count > 0) {
                    // Повторяющаяся: count в диапазоне 1..127
                    int value = in.read();
                    if (value == -1) {
                        throw new IllegalArgumentException("Unexpected EOF in repeated sequence");
                    }
                    for (int i = 0; i < count; i++) {
                        out.write(value);
                    }
                } else if (count < 0) {
                    // Неповторяющаяся: длина = -count, в диапазоне 1..128
                    int length = -count;
                    for (int i = 0; i < length; i++) {
                        int value = in.read();
                        if (value == -1) {
                            throw new IllegalArgumentException("Unexpected EOF in non-repeated sequence");
                        }
                        out.write(value);
                    }
                } else {
                    throw new IllegalArgumentException("Invalid RLE count: 0");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Decompression failed", e);
        }
    }
}
