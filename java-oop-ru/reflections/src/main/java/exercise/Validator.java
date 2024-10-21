package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object obj) {
        List<String> notValidFields = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true); // Делаем поле доступным
                try {
                    if (field.get(obj) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }
    public static Map<String, List<String>> advancedValidate(Object obj){
        Map<String, List<String>> notValidFields;
        notValidFields = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            List<String> errors = new ArrayList<>();
            field.setAccessible(true); // Делаем поле доступным
            try {
                Object value = field.get(obj);

                // Проверка на аннотацию @NotNull
                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    errors.add("can not be null");
                }

                // Проверка на аннотацию @MinLength
                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength minLength = field.getAnnotation(MinLength.class);
                    if (value instanceof String && ((String) value).length() < minLength.minLength()) {
                        errors.add("length less than " + minLength.minLength());
                    }
                }

                // Если есть ошибки, добавляем их в карту
                if (!errors.isEmpty()) {
                    notValidFields.put(field.getName(), errors);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notValidFields;
    }
}
// END
