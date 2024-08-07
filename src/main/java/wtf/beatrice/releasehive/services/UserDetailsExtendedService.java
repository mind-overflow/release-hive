package wtf.beatrice.releasehive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.models.User;
import wtf.beatrice.releasehive.repositories.UserRepository;

@Service
public class UserDetailsExtendedService {

    private final UserRepository userRepository;

    public UserDetailsExtendedService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
