package wtf.beatrice.releasehive.services;

import org.apache.coyote.BadRequestException;
import wtf.beatrice.releasehive.dtos.LoginUserDto;
import wtf.beatrice.releasehive.dtos.RegisterUserDto;
import wtf.beatrice.releasehive.models.User;

public interface AccountService
{

    User register(RegisterUserDto user) throws BadRequestException;

    User login(LoginUserDto user);
}
