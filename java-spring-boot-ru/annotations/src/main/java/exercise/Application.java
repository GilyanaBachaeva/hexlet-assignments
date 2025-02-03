package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Class<?> addressClass = address.getClass();
        Method[] methods = addressClass.getDeclaredMethods();
        for (Method method : methods) {
            // Проверяем, помечен ли метод аннотацией @Inspect
            if (method.isAnnotationPresent(Inspect.class)) {
                // Получаем имя метода и тип возвращаемого значения
                String methodName = method.getName();
                String returnType = method.getReturnType().getSimpleName();

                // Выводим информацию о методе
                System.out.println("Method " + methodName + " returns a value of type " + returnType);
            }
        }
        // END
    }
}
