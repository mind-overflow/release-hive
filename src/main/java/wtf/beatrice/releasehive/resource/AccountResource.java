package wtf.beatrice.releasehive.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wtf.beatrice.releasehive.util.JsonUtil;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.service.AccountService;

@RequestMapping("/api/v1/users")
@RestController
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
        accountService.registerUser(user);
        return JsonUtil.convertToJson(user);
    }
}
