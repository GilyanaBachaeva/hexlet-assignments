package exercise.controller.users;

import java.util.ArrayList;
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
@RequestMapping("/api/users")
public class PostsController {
    private List<Post> posts = new ArrayList<Post>();
    // Получение списка всех постов для пользователя с указанным id
    @GetMapping("/{id}/posts")
    public List<Post> getPostsByUserId(@PathVariable int id) {
        List<Post> allPosts = Data.getPosts();
        List<Post> userPosts = new ArrayList<>(); // Создаем новый список для постов пользователя
        for (Post post : allPosts) {
            if (post.getUserId() == id) {
                userPosts.add(post); // Добавляем пост в список, если userId совпадает
            }
        }
        return userPosts; // Возвращаем список постов пользователя
    }

    // Создание нового поста для пользователя с указанным id
    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable int id, @RequestBody Post newPost) {
        newPost.setUserId(id); // Устанавливаем userId из маршрута
        List<Post> posts = Data.getPosts(); // Получаем текущий список постов
        posts.add(newPost); // Добавляем новый пост в список
        return newPost; // Возвращаем созданный пост
    }
}
// END
