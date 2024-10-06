package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;
    private Map<String, String> storage;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        this.storage = new HashMap<>(initialData);
        loadFromFile();
    }

    private void loadFromFile() {
        String fileContent = Utils.readFile(filePath);
        if (!fileContent.isEmpty()) {
            storage = Utils.deserialize(fileContent);
        }
    }

    private void saveToFile() {
        String serializedData = Utils.serialize(storage);
        Utils.writeFile(filePath, serializedData);
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }
    @Override
    public void unset(String key) {
        storage.remove(key);
    }
    @Override
    public String get(String key, String nokeyinfo) {
        return storage.getOrDefault(key, "default");
    }
    @Override
    public Map<String, String> toMap() {
        return storage;
    }
}
// END
