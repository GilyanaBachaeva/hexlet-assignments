package exercise;

import io.javalin.Javalin;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();
    private static final Gson gson = new Gson();

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // Обработчик для получения списка пользователей
        app.get("/users", ctx -> {
            // Получаем параметры страницы и количества записей на странице
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var perPage = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);


            // Вычисляем индекс начала и конца для выборки пользователей
            int start = (page - 1) * perPage;
            int end = Math.min(start + perPage, USERS.size());

            // Проверяем, что индекс начала не выходит за пределы списка
            if (start >= USERS.size() || start < 0) {
                ctx.status(404).result("No users found for the requested page.");
                return;
            }

            // Получаем подсписок пользователей
            List<Map<String, String>> paginatedUsers = USERS.subList(start, end);

            // Возвращаем результат в формате JSON
            ctx.result(gson.toJson(paginatedUsers));
            ctx.contentType("application/json");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
