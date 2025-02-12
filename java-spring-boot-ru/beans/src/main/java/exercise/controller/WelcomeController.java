package exercise.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
import org.springframework.beans.factory.annotation.Autowired;
import exercise.daytime.Daytime;

@RestController
public class WelcomeController {

    private final Daytime daytime;

    @Autowired
    public WelcomeController(Daytime daytime) {
        this.daytime = daytime;
    }

    // BEGIN
    @GetMapping("/welcome")
    public String welcome() {
        return daytime.getName(); // Возвращаем приветствие в зависимости от времени суток
    }
    // END
}
// END
