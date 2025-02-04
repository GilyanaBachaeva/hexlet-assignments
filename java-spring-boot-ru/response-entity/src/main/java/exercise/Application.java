package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    // Получение списка всех постов с поддержкой пейджинга
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int limit) {
        int fromIndex = page * limit;
        int toIndex = Math.min(fromIndex + limit, posts.size());

        if (fromIndex >= posts.size()) {
            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(posts.size()))
                    .body(List.of()); // Возвращаем пустой список, если индекс страницы выходит за пределы
        }

        List<Post> pagedPosts = posts.subList(fromIndex, toIndex);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(pagedPosts);
    }

    // Получение конкретного поста по ID
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .map(post -> ResponseEntity.ok(post)) // Возвращаем статус 200, если пост найден
                .orElse(ResponseEntity.notFound().build()); // Возвращаем статус 404, если пост не найден
    }

    // Создание нового поста
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post); // Возвращаем статус 201
    }

    // Обновление поста
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        for (Post existingPost : posts) {
            if (existingPost.getId().equals(id)) {
                existingPost.setTitle(updatedPost.getTitle());
                existingPost.setBody(updatedPost.getBody());
                return ResponseEntity.ok(existingPost); // Возвращаем статус 200
            }
        }
        return ResponseEntity.noContent().build(); // Возвращаем статус 204, если пост не найден
    }

    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
