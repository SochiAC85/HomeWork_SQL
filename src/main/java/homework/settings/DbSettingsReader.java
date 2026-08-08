package homework.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbSettingsReader{

    public Map<String, String> getSettings() {
            Properties properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                throw new RuntimeException();
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        Map<String, String> result = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            result.put(key, properties.getProperty(key));
        }
        return result;
    }
}



