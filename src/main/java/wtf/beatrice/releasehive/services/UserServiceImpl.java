package wtf.beatrice.releasehive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.models.User;
import wtf.beatrice.releasehive.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public void deleteUser(String email) {
        if (userRepository.findByEmail(email).isEmpty()) {
            throw new UsernameNotFoundException(email);
        }
        userRepository.delete(userRepository.findByEmail(email).get());
    }
}
