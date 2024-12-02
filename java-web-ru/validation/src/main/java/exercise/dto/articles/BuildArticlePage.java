package exercise.dto.articles;

import io.javalin.validation.ValidationError;
import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

// BEGIN
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BuildArticlePage {
    private String title;
    private String content;
}
// END
