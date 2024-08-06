package wtf.beatrice.releasehive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TryController {

    @GetMapping("/try")
    public String tryMethod() {
        return "Hello World!";
    }
}
