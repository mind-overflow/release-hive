package wtf.beatrice.releasehive.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import wtf.beatrice.releasehive.models.User;

import java.util.List;

public interface UserService
{
    List<User> getAllUsers();

    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User loadUserByEmail(String email) throws UsernameNotFoundException;
}
