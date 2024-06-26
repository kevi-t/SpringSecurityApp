package com.project.lab.Services;

import com.project.lab.dtos.AuthenticationDTO;
import com.project.lab.jwt.JwtTokenUtil;
import com.project.lab.models.Users;
import com.project.lab.repositories.UserRepository;
import com.project.lab.responses.UniversalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    public final UserRepository userRepository;

    public UniversalResponse login(AuthenticationDTO request) {
        try {
            Users user = userRepository.findUsersByEmail(request.getEmail());
            if (user == null) {
                log.info("User not found please register");
                return UniversalResponse.builder().message("User not found please register").status("0").build();
            }
            else {
                try {
                    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    String jwt = jwtTokenUtil.createToken(request.getEmail());
                    log.info("login successful");
                    return UniversalResponse.builder().message("login successful").status("0").data(jwt).build();
                }
                catch (AuthenticationException e) {
                    log.error("Authentication failed: " + e.getMessage());
                    return UniversalResponse.builder().message("User or password incorrect").status("0").build();
                }
            }
        }
        catch (DataAccessException e) {
            log.error("Database access error: " + e.getMessage());
            return UniversalResponse.builder().message("Database access error").status("1").build();
        }
        catch (RuntimeException e) {
            log.error("Unexpected error occurred: " + e.getMessage());
            return UniversalResponse.builder().message("Unexpected error occurred").status("1").build();
        }
    }
}