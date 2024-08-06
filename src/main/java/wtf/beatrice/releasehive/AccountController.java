package wtf.beatrice.releasehive;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AccountController {

    private final AccountService accountService = new AccountService();


    @PostMapping("/register")
    public String register(
            @RequestParam(name = "username") String username,
            @RequestParam(name="password") String password)
    {

        UUID id = UUID.randomUUID();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUuid(id);
        accountService.registerUser(user);
        return "user registered, " + username + ", " + password + ", " + id.toString();
    }
}
