package com.prapp.examplesecurityjwtbasic.business;


import com.prapp.examplesecurityjwtbasic.expose.dto.UserDto;
import com.prapp.examplesecurityjwtbasic.security.JsonResponse;

public interface UserService {

    UserDto.Response register(UserDto.Request request);
    JsonResponse login(UserDto.LoginRequest request);

}
