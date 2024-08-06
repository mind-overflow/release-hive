package wtf.beatrice.releasehive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
public class AccountResource {

    private final AccountService accountService = new AccountService();


    @PostMapping(
            value="/register",
            produces="application/json")
    public String register(@RequestBody User user)
    {
        UUID id = UUID.randomUUID();
        user.setUuid(id);
        accountService.registerUser(user);

        return JsonUtil.convertToJson(user);
    }
}
