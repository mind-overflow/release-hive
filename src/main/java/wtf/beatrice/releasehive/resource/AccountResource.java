package wtf.beatrice.releasehive.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtf.beatrice.releasehive.dto.LoginUserDto;
import wtf.beatrice.releasehive.dto.RegisterUserDto;
import wtf.beatrice.releasehive.model.LoginResponse;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.service.AccountService;
import wtf.beatrice.releasehive.service.JWTService;

@RestController
@RequestMapping("/api/v1/auth")
public class AccountResource {

    private final AccountService accountService;
    private final JWTService jwtService;

    public AccountResource(
            @Autowired AccountService accountService,
            @Autowired JWTService jwtService) {
        this.accountService = accountService;
        this.jwtService = jwtService;
    }

    @PostMapping(
            value="/register",
            produces="application/json")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto userDto)
    {
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
