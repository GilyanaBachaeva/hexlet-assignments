package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Метод для сохранения объекта Car в файл
    public static void save(Path path, Car car) {
        try {
            String json = objectMapper.writeValueAsString(car);
            Files.write(path, json.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для извлечения объекта Car из файла
    public static Car extract(Path path) {
        try {
            String json = new String(Files.readAllBytes(path));
            return objectMapper.readValue(json, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
// END
