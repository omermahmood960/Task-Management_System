package app.com.login.service;

import app.com.login.dto.LoginRequestDto;
import app.com.login.dto.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}