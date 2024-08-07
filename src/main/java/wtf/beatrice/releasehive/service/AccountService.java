package wtf.beatrice.releasehive.service;

import wtf.beatrice.releasehive.dto.LoginUserDto;
import wtf.beatrice.releasehive.dto.RegisterUserDto;
import wtf.beatrice.releasehive.model.User;

public interface AccountService
{

    User register(RegisterUserDto user);

    User login(LoginUserDto user);
}
