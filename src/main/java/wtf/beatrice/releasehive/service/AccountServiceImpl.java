package wtf.beatrice.releasehive.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.db.HibernateManager;
import wtf.beatrice.releasehive.dto.LoginUserDto;
import wtf.beatrice.releasehive.dto.RegisterUserDto;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.repository.UserRepository;
import wtf.beatrice.releasehive.util.JsonUtil;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AccountServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired AuthenticationManager authenticationManager,
            @Autowired PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterUserDto userDto) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User login(LoginUserDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        return userRepository.findByEmail(user.getEmail())
                .orElseThrow();
    }


}
