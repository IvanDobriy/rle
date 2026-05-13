package ru.otus.danilchenko.data.rle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.otus.danilchenko.domain.data.IRle;
import ru.otus.danilchenko.lib.v1.rle.RleCompressorV1;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RleDataServiceTest {

    @Test
    void compressAndDecompressRoundTrip(@TempDir Path tempDir) throws Exception {
        RleCompressorV1 compressor = new RleCompressorV1();
        IRle service = new RleDataService(compressor, compressor);

        Path initial = tempDir.resolve("input.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("output.txt");

        String content = "AAABBCAAAABBBCCCCDDD";
        Files.writeString(initial, content);

        service.compress(initial, compressed);
        service.decompress(compressed, decompressed);

        assertEquals(content, Files.readString(decompressed));
    }

    @Test
    void compressEmptyFile(@TempDir Path tempDir) throws Exception {
        RleCompressorV1 compressor = new RleCompressorV1();
        IRle service = new RleDataService(compressor, compressor);

        Path initial = tempDir.resolve("empty.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("output.txt");

        Files.createFile(initial);

        service.compress(initial, compressed);
        service.decompress(compressed, decompressed);

        assertEquals(0, Files.size(decompressed));
    }

    @Test
    void constructorThrowsOnNullCompressor() {
        assertThrows(NullPointerException.class, () -> new RleDataService(null, new RleCompressorV1()));
    }

    @Test
    void constructorThrowsOnNullDecompressor() {
        assertThrows(NullPointerException.class, () -> new RleDataService(new RleCompressorV1(), null));
    }
}
