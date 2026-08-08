package homework.animals;

import java.util.ArrayList;
import java.util.List;

public enum Color {

    UNDEFINED("Неизвестный"),
    WHITE("Белый"),
    BLACK("Черный"),
    BROWN("Коричневый"),
    RED("Рыжий");

    private final String value;
    public static final List<String> VALUES = collectValues();

    private static List<String> collectValues() {
        List<String> result = new ArrayList<>();

        for (Color type : Color.values()) {
            result.add(type.value);
        }
        return result;
    }

    Color(String value) {
        this.value = value;
    }

    public static Color fromString(String color) {
        if (color == null || color.trim().isEmpty()) {
            return UNDEFINED;
        }
        String normalized = color.trim().toLowerCase();
        for (Color c : values()) {
            if (c.getValue().toLowerCase().equals(normalized)) {
                return c;
            }
        }
        return UNDEFINED;
    }

    public String getValue() {
        return value;
    }
}
