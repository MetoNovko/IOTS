package com.mps.services;

import com.mps.models.User;
import com.mps.transferables.DTOs.User.ChangePasswordRequest;
import com.mps.transferables.DTOs.User.CreateUserRequest;
import com.mps.transferables.DTOs.Security.JwtAuthRequest;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String username);

    Optional<User> getUserByEmail(String email);

    User registerUser(CreateUserRequest user);

    String registerUserAndAuthenticate(CreateUserRequest user);

    String authenticateUser(JwtAuthRequest request);

    boolean changePassword(String username, ChangePasswordRequest passwordRequest);
}
