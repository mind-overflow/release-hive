package wtf.beatrice.releasehive.services;

import wtf.beatrice.releasehive.dtos.LoginUserDto;
import wtf.beatrice.releasehive.dtos.RegisterUserDto;
import wtf.beatrice.releasehive.models.User;

public interface AccountService
{

    User register(RegisterUserDto user);

    User login(LoginUserDto user);
}
