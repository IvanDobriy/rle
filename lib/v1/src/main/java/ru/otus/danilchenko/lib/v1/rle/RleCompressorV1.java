package ru.otus.danilchenko.lib.v1.rle;

import ru.otus.danilchenko.lib.api.rle.IRleCompressor;
import ru.otus.danilchenko.lib.api.rle.IRleDecompressor;
import ru.otus.danilchenko.lib.api.stack.IStack;
import ru.otus.danilchenko.lib.v1.array.SingleArray;
import ru.otus.danilchenko.lib.v1.stack.ArrayStack;

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

            IStack<Integer> lookahead = new ArrayStack<>(new SingleArray<>(10));
            int current = readByte(lookahead, in);
            if (current == -1) {
                return;
            }

            while (true) {
                int next = readByte(lookahead, in);

                if (next == -1) {
                    out.write(-1);
                    out.write(current);
                    break;
                }

                if (current == next) {
                    // Повторяющаяся последовательность: 1..127
                    int count = 2;
                    while (count < 127) {
                        int b = readByte(lookahead, in);
                        if (b == current) {
                            count++;
                        } else {
                            unreadByte(lookahead, b);
                            break;
                        }
                    }
                    out.write(count);
                    out.write(current);

                    current = readByte(lookahead, in);
                    if (current == -1) {
                        break;
                    }
                } else {
                    // Неповторяющаяся последовательность: -128..-1 (длина 1..128)
                    IStack<Byte> seq = new ArrayStack<>(new SingleArray<>(128));
                    seq.push((byte) current);
                    seq.push((byte) next);

                    while (seq.size() < 128) {
                        int b = readByte(lookahead, in);
                        if (b == -1) {
                            break;
                        }

                        int peek = readByte(lookahead, in);
                        if (peek == b) {
                            unreadByte(lookahead, peek);
                            unreadByte(lookahead, b);
                            break;
                        } else {
                            if (peek != -1) {
                                unreadByte(lookahead, peek);
                            }
                            seq.push((byte) b);
                        }
                    }

                    int count = seq.size();
                    out.write(-count);

                    IStack<Byte> rev = new ArrayStack<>(new SingleArray<>(count));
                    while (seq.size() > 0) {
                        rev.push(seq.pop());
                    }
                    while (rev.size() > 0) {
                        out.write(rev.pop());
                    }

                    current = readByte(lookahead, in);
                    if (current == -1) {
                        break;
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
                    IStack<Byte> seq = new ArrayStack<>(new SingleArray<>(length));
                    for (int i = 0; i < length; i++) {
                        int value = in.read();
                        if (value == -1) {
                            throw new IllegalArgumentException("Unexpected EOF in non-repeated sequence");
                        }
                        seq.push((byte) value);
                    }

                    IStack<Byte> rev = new ArrayStack<>(new SingleArray<>(length));
                    while (seq.size() > 0) {
                        rev.push(seq.pop());
                    }
                    while (rev.size() > 0) {
                        out.write(rev.pop());
                    }
                } else {
                    throw new IllegalArgumentException("Invalid RLE count: 0");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Decompression failed", e);
        }
    }

    private int readByte(IStack<Integer> lookahead, InputStream in) throws IOException {
        if (lookahead.size() > 0) {
            return lookahead.pop();
        }
        return in.read();
    }

    private void unreadByte(IStack<Integer> lookahead, int b) {
        lookahead.push(b);
    }
}
