package exercise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/404")
    public String error404() {
        return "404"; // возвращает 404.html
    }
}
