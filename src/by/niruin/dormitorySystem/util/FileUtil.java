package by.niruin.dormitorySystem.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtil {

    public static void writeString(Path path, String data) {
        ensureFileExist(path);

        try (BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
            bf.write(data);
        } catch (IOException e) {
            throw new RuntimeException("File with path: " + path + " writing error!", e);
        }
    }

    private static void ensureFileExist(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException("File with path: " + path + " creation error!", e);
            }
        }
    }

    public static String readString(Path path) throws FileNotFoundException {
        ensureFileExist(path);

        String data;
        try {
            data = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
