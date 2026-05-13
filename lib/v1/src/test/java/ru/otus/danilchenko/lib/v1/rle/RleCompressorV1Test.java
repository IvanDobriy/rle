package ru.otus.danilchenko.lib.v1.rle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RleCompressorV1Test {

    private final RleCompressorV1 compressor = new RleCompressorV1();

    @Test
    void compressAndDecompressRoundTrip(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        String content = "AAABBCAAAABBBCCCCDDD";
        Files.writeString(initial, content);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        String result = Files.readString(decompressed);
        assertEquals(content, result);
    }

    @Test
    void compressEmptyFile(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("empty.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        Files.createFile(initial);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(0, Files.size(decompressed));
    }

    @Test
    void onlyNonRepeatingSequence(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        String content = "ABCDEFGH";
        Files.writeString(initial, content);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(content, Files.readString(decompressed));
    }

    @Test
    void onlyRepeatingSequence(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        String content = "AAAAAAAAAA";
        Files.writeString(initial, content);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(content, Files.readString(decompressed));
    }

    @Test
    void longRepeatingSequenceSplitsAt127(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append('A');
        }
        Files.writeString(initial, sb.toString());

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(sb.toString(), Files.readString(decompressed));
    }

    @Test
    void longNonRepeatingSequenceSplitsAt128(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sb.append((char) ('A' + (i % 26)));
        }
        Files.writeString(initial, sb.toString());

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(sb.toString(), Files.readString(decompressed));
    }

    @Test
    void compressedSmallerThanOriginalForRepeating(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append('A');
        }
        Files.writeString(initial, sb.toString());

        compressor.compress(initial, compressed);

        assertTrue(Files.size(compressed) < Files.size(initial));
    }

    @Test
    void singleByte(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        Files.writeString(initial, "X");

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals("X", Files.readString(decompressed));
    }

    @Test
    void nonRepeatingExactly128Bytes(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            sb.append((char) ('A' + (i % 26)));
        }
        Files.writeString(initial, sb.toString());

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(sb.toString(), Files.readString(decompressed));
    }

    @Test
    void nonRepeatingExactly129Bytes(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 129; i++) {
            sb.append((char) ('A' + (i % 26)));
        }
        Files.writeString(initial, sb.toString());

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(sb.toString(), Files.readString(decompressed));
    }

    @Test
    void nonRepeatingEndsWithSingleByte(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        String content = "ABCDEFGZ";
        Files.writeString(initial, content);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(content, Files.readString(decompressed));
    }

    @Test
    void nonRepeatingFollowedByRepeating(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        String content = "ABCDEFZZZZZ";
        Files.writeString(initial, content);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(content, Files.readString(decompressed));
    }

    @Test
    void alternatingNonRepeatingAndRepeating(@TempDir Path tempDir) throws Exception {
        Path initial = tempDir.resolve("initial.txt");
        Path compressed = tempDir.resolve("compressed.rle");
        Path decompressed = tempDir.resolve("decompressed.txt");

        String content = "ABCXXXXDEFYYYYYGH";
        Files.writeString(initial, content);

        compressor.compress(initial, compressed);
        compressor.decompress(compressed, decompressed);

        assertEquals(content, Files.readString(decompressed));
    }
}
