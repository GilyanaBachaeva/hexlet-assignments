package exercise;

import io.javalin.Javalin;

import java.util.List;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        Javalin app = Javalin.create(); // Создаем экземпляр Javalin

        // Обработчик для получения списка телефонов
        app.get("/phones", ctx -> {
            List<String> phones = Data.getPhones(); // Получаем список телефонов
            ctx.json(phones); // Сериализуем список телефонов в JSON и отправляем в ответ
        });

        // Обработчик для получения списка доменов
        app.get("/domains", ctx -> {
            List<String> domains = Data.getDomains(); // Получаем список доменных имен
            ctx.json(domains); // Сериализуем список доменных имен в JSON и отправляем в ответ
        });

        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
