package wtf.beatrice.releasehive.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.config.InternalConfiguration;
import wtf.beatrice.releasehive.dtos.EditUsernameAccountDto;
import wtf.beatrice.releasehive.dtos.LoginUserDto;
import wtf.beatrice.releasehive.dtos.RegisterUserDto;
import wtf.beatrice.releasehive.models.User;
import wtf.beatrice.releasehive.repositories.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AccountServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired AuthenticationManager authenticationManager,
            @Autowired PasswordEncoder passwordEncoder)
    {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterUserDto userDto) throws BadRequestException {

        if (userDto.getEmail() == null ||
                userDto.getEmail().isEmpty() ||
                userDto.getPassword() == null ||
                userDto.getPassword().isEmpty() ||
                userDto.getUsername() == null ||
                userDto.getUsername().isEmpty()) {

            throw new BadRequestException("Please provide a valid email, password, and username");
        }

        if(!userDto.getEmail().matches(InternalConfiguration.EMAIL_REGEX_RCF)) {
            throw new BadRequestException("Invalid email format");
        }

        if(!userDto.getUsername().matches(InternalConfiguration.USERNAME_REGEX)) {
            throw new BadRequestException("Username contains invalid characters");
        }

        if(!userDto.getPassword().matches(InternalConfiguration.PASSWORD_REGEX)) {
            throw new BadRequestException("Invalid password format");
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new BadRequestException("An account already exists with this email");
        }

        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new BadRequestException("Username already in use");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserDto userDto) throws BadRequestException {

        if (userDto.getEmail() == null ||
                userDto.getEmail().isEmpty() ||
                userDto.getPassword() == null ||
                userDto.getPassword().isEmpty()) {

            throw new BadRequestException("Please provide a valid email and password");
        }

        if(!userDto.getEmail().matches(InternalConfiguration.EMAIL_REGEX_RCF)) {
            throw new BadRequestException("Invalid email format");
        }

        if(!userDto.getPassword().matches(InternalConfiguration.PASSWORD_REGEX)) {
            throw new BadRequestException("Invalid password format");
        }

        if (!userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new BadRequestException("No account registered with this email");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()));

        return userRepository.findByEmail(userDto.getEmail())
                .orElseThrow();
    }

    @Override
    public String changeUsername(EditUsernameAccountDto editData) {
        User user = userRepository
                .findById(editData.getUuid())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setUsername(editData.getUsername());
        userRepository.save(user);
        return user.getUsername();
    }

}
