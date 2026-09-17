package app.com.login.service.Impl;

import app.com.entities.User;
import app.com.login.dto.LoginRequestDto;
import app.com.login.dto.LoginResponseDto;
import app.com.login.service.LoginService;
import app.com.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<User> userOptional = this.userRepository.findByEmail(loginRequestDto.getUserEmail());
        if (userOptional.isEmpty()) {
            return new LoginResponseDto("Invalid Email or Password");
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            return new LoginResponseDto("Invalid Email or Password");
        }
        return new LoginResponseDto("Login Successful");
    }

}