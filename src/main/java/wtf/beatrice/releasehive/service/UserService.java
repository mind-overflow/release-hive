package wtf.beatrice.releasehive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wtf.beatrice.releasehive.model.User;
import wtf.beatrice.releasehive.repository.UserRepository;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
