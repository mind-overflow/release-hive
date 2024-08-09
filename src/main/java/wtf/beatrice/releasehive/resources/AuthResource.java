package wtf.beatrice.releasehive.resources;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtf.beatrice.releasehive.dtos.LoginUserDto;
import wtf.beatrice.releasehive.dtos.RegisterUserDto;
import wtf.beatrice.releasehive.models.LoginResponse;
import wtf.beatrice.releasehive.models.User;
import wtf.beatrice.releasehive.services.AccountService;
import wtf.beatrice.releasehive.services.JWTService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {

    private final AccountService accountService;
    private final JWTService jwtService;

    public AuthResource(
            @Autowired AccountService accountService,
            @Autowired JWTService jwtService) {
        this.accountService = accountService;
        this.jwtService = jwtService;
    }

    @PostMapping(
            value="/register",
            produces="application/json")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto userDto) throws BadRequestException {
        User user = accountService.register(userDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping(
            value="/login",
            produces="application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto userDto)
    {
        User authenticatedUser = accountService.login(userDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
