package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();
    //список всех постов, написанных пользователем с таким же userId, как id в маршруте
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/posts")
    public Post create(@PathVariable Integer userId, @RequestBody Post post) {
        post.setUserId(userId);
        posts.add(post);
        return post;
    }
// создание нового поста, привязанного к пользователю по id. Код должен возвращать статус 201, тело запроса должно
// содержать slug, title и body. Обратите внимание, что userId берется из маршрута
    @GetMapping("/users/{userId}/posts")
    public List<Post> show(@PathVariable Integer userId) {
        return posts.stream()
                .filter(p -> p.getUserId() == userId).toList();
    }
}
// END
