package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
            ctx.render("articles/build.jte", model("article", new BuildArticlePage("", "")));
        });
        app.post("/articles", ctx -> {
            String title = ctx.formParam("title");
            String content = ctx.formParam("content");

            // Валидация данных
            if (title == null || title.length() < 2) {
                ctx.status(422);
                ctx.render("articles/build.jte", model("article", new BuildArticlePage(title, content), "error", "Название не должно быть короче двух символов"));
                return;
            }

            if (content == null || content.length() < 10) {
                ctx.status(422);
                ctx.render("articles/build.jte", model("article", new BuildArticlePage(title, content), "error", "Содержимое статьи должно быть не короче 10 символов"));
                return;
            }

            // Проверка на уникальность названия статьи
            if (ArticleRepository.findByTitle(title).isPresent()) {
                ctx.status(422);
                ctx.render("articles/build.jte", model("article", new BuildArticlePage(title, content), "error", "Статья с таким названием уже существует"));
                return;
            }

            // Создание статьи
            Article newArticle = new Article(title, content);
            ArticleRepository.save(newArticle);

            // Редирект на страницу со списком статей
            ctx.redirect("/articles");
        });

        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
