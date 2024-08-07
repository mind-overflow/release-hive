package wtf.beatrice.releasehive.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.service.AccountService;

@RestController
@RequestMapping("/api/v1/users")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping(
            value="/register",
            produces="application/json")
    public String register(@RequestBody User user)
    {
        return accountService.registerUser(user);
    }
}
