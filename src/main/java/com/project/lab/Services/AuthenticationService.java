package com.project.lab.Services;

import com.project.lab.dtos.AuthenticationDTO;
import com.project.lab.jwt.JwtTokenUtil;
import com.project.lab.models.Users;
import com.project.lab.repositories.UserRepository;
import com.project.lab.responses.UniversalResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    public UniversalResponse response;
    public final UserRepository userRepository;

    public UniversalResponse login(AuthenticationDTO request) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (request.getEmail() == null) {
            log.info("User not found please register");
            return UniversalResponse.builder().message("User not found please register").status("0").build();
        }
//        else if (authentication == null) {
//            return  UniversalResponse.builder().message("User or password incorrect").status("0").build();
//        }
        else {
            String jwt = jwtTokenUtil.createToken(request.getEmail());
            log.info("login successful");
            return UniversalResponse.builder().message("login successful").status("0").data(jwt).build();
        }
//        try {

//        }
//        catch (Exception exception){
//            log.info("Login error" +exception);
//
//        }
    }
}