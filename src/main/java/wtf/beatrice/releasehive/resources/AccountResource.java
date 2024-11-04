package wtf.beatrice.releasehive.resources;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wtf.beatrice.releasehive.dtos.EditUsernameAccountDto;
import wtf.beatrice.releasehive.services.AccountService;
import wtf.beatrice.releasehive.services.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
public class AccountResource {

    private final AccountService accountService;
    private final UserService userService;

    public AccountResource(
            @Autowired AccountService accountService,
            @Autowired UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping(
            value="/edit",
            produces= MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> register(@RequestBody EditUsernameAccountDto userDto) throws BadRequestException {
        String username = accountService.changeUsername(userDto);
        return ResponseEntity.ok(username);
    }

    @DeleteMapping(
            value = "/delete/{id}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String id) {
        UUID uuid = UUID.fromString(id);
        boolean deleted = userService.deleteUser(uuid);
        return ResponseEntity.ok(deleted);
    }
}
