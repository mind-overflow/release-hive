package wtf.beatrice.releasehive.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource
{
    private final UserService userService;

    public UserResource(@Autowired UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping(
            value = "/me",
            produces = "application/json")
    public ResponseEntity<User> authenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping(
            value = "/all",
            produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
