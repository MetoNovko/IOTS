package com.mps.controllers;

import com.mps.services.UserService;
import com.mps.transferables.DTOs.User.ChangePasswordRequest;
import com.mps.transferables.DTOs.User.CreateUserRequest;
import com.mps.transferables.DTOs.Security.JwtAuthRequest;
import com.mps.transferables.DTOs.User.CreateUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CreateUserResponse registerUser(@RequestBody CreateUserRequest createUserRequest) {
        return new CreateUserResponse(
                createUserRequest.getUsername(),
                this.userService.registerUserAndAuthenticate(createUserRequest)
        );
    }


    @PostMapping("/authenticate")
    public String authenticateUser(@RequestBody JwtAuthRequest jwtAuthRequest) {
        return this.userService.authenticateUser(jwtAuthRequest);
    }

    @PostMapping("/changePassword")
    public void changePassword(Principal principal, @RequestBody ChangePasswordRequest passwordRequest) {
        this.userService.changePassword(principal.getName(), passwordRequest);
    }

}
