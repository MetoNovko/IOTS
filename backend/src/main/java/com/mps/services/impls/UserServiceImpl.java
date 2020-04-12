package com.mps.services.impls;

import com.mps.configurations.security.JwtUtil;
import com.mps.models.User;
import com.mps.repositories.UserRepository;
import com.mps.services.UserService;
import com.mps.transferables.DTOs.User.ChangePasswordRequest;
import com.mps.transferables.DTOs.User.CreateUserRequest;
import com.mps.transferables.DTOs.Security.JwtAuthRequest;
import com.mps.transferables.exceptions.DuplicateEmailException;
import com.mps.transferables.exceptions.DuplicateUsernameException;
import com.mps.transferables.exceptions.UserNotFoundException;
import com.mps.transferables.exceptions.WrongPasswordException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUserDetailsService jwtUserDetailsService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Optional<User> getUser(String username) {
        return this.userRepository.findById(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User registerUser(CreateUserRequest userReq) {
        if (this.userRepository.findById(userReq.getUsername()).isPresent())
            throw new DuplicateUsernameException();

        if (this.userRepository.findByEmail(userReq.getEmail()).isPresent())
            throw new DuplicateEmailException();

        return this.userRepository.save(new User(userReq.getUsername(),
                userReq.getEmail(),
                userReq.getFirstName(),
                userReq.getLastName(),
                this.passwordEncoder.encode(userReq.getPassword()),
                new HashSet<>()
        ));
    }

    @Override
    public String registerUserAndAuthenticate(CreateUserRequest request) {
        this.registerUser(request);

        this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        return this.jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                request.getUsername(), request.getPassword(), new ArrayList<>()));
    }

    @Override
    public String authenticateUser(JwtAuthRequest request) {
        this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        return this.jwtUtil.generateToken(new org.springframework.security.core.userdetails
                .User(request.getUsername(), request.getPassword(), new ArrayList<>()));
    }

    @Override
    public boolean changePassword(String username, ChangePasswordRequest changePasswordRequest) {
        User user = this.getUser(username)
                .orElseThrow(UserNotFoundException::new);

        if (!this.passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword()))
            throw new WrongPasswordException();

        final String encodedNewPassword = this.passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setPassword(encodedNewPassword);

        this.userRepository.save(user);

        return true;
    }
}
