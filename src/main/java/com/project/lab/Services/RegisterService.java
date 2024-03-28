package com.project.lab.Services;

import com.project.lab.dtos.RegisterDTO;
import com.project.lab.enums.UserRole;
import com.project.lab.models.Users;
import com.project.lab.repositories.UserRepository;
import com.project.lab.responses.UniversalResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UniversalResponse register(RegisterDTO request) {
        try{
            if (userRepository.findUsersByEmail(request.getEmail()) != null) {
                String email = request.getEmail();
                return UniversalResponse.builder()
                        .message("User with email"+email+"exists login")
                        .status("0")
                        .build();
            }
            else {
                Users adminUser = Users.builder().
                        name("James Mungau").
                        email("admin@gmail.com").
                        password(passwordEncoder.encode("java")).
                        userRole(UserRole.ADMIN).enabled(true).locked(false).build();
                var user = Users.builder().
                        name(request.getName()).
                        email(request.getEmail()).
                        password(passwordEncoder.encode(request.getPassword())).
                        userRole(UserRole.USER).enabled(false).locked(true).build();
                userRepository.save(adminUser);
                userRepository.save(user);
                return UniversalResponse.builder().message("Registration successful").status("0").build();
            }
        }
        catch (Exception e){
            return  UniversalResponse.builder().message("Registration failed").status("0").build();
        }
    }
}