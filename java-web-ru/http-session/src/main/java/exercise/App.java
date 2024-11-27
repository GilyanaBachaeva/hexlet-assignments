package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        // Обработчик для получения списка пользователей
        app.get("/users", ctx -> {
            // Получаем параметры страницы и количества записей на странице
            int page = ctx.queryParam("page").equals("") ? 1 : Integer.parseInt(ctx.queryParam("page"));
            int perPage = ctx.queryParam("per").equals("") ? 5 : Integer.parseInt(ctx.queryParam("per"));

            // Вычисляем индекс начала и конца для выборки пользователей
            int start = (page - 1) * perPage;
            int end = Math.min(start + perPage, USERS.size());

            // Получаем подсписок пользователей
            List<Map<String, String>> paginatedUsers = USERS.subList(start, end);

            // Возвращаем результат в формате JSON
            ctx.json(paginatedUsers);
            ctx.contentType("application/json");
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
