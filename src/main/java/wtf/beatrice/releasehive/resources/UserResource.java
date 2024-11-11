package wtf.beatrice.releasehive.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import wtf.beatrice.releasehive.models.User;
import wtf.beatrice.releasehive.services.UserService;

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
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> authenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping(
            value = "/all",
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
