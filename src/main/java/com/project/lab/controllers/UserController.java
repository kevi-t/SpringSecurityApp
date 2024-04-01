package com.project.lab.controllers;

import com.project.lab.Services.AuthenticationService;
import com.project.lab.Services.RegisterService;
import com.project.lab.dtos.AuthenticationDTO;
import com.project.lab.dtos.RegisterDTO;
import com.project.lab.responses.UniversalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security/")
@AllArgsConstructor
public class UserController {

    private final RegisterService registerService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UniversalResponse> register(@RequestBody @Valid RegisterDTO request)
    {
        try{
            return ResponseEntity.ok(registerService.register(request));
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<UniversalResponse> login(@RequestBody @Valid AuthenticationDTO request)
    {
        try{
            return ResponseEntity.ok(authenticationService.login(request));
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    public String Test(){
        return ("Connected");
    }
}
