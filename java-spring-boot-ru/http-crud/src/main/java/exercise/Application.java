package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Post> getPosts(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int limit) {
        int fromIndex = page * limit;
        int toIndex = Math.min(fromIndex + limit, posts.size());

        if (fromIndex >= posts.size()) {
            return List.of(); // Возвращаем пустой список, если индекс страницы выходит за пределы
        }

        return posts.subList(fromIndex, toIndex);
    }

    // Получение конкретного поста по ID
    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null); // Возвращаем null, если пост не найден
    }

    // Создание нового поста
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    // Обновление поста
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        Optional<Post> existingPostOpt = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setBody(updatedPost.getBody());
            return existingPost;
        } else {
            return null; // Возвращаем null, если пост не найден
        }
    }

    // Удаление поста
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }
    // END
}
