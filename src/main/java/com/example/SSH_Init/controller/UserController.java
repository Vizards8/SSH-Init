package com.example.SSH_Init.controller;

import com.example.SSH_Init.dto.LoginRequest;
import com.example.SSH_Init.dto.SignupRequest;
import com.example.SSH_Init.dto.BaseResponse;
import com.example.SSH_Init.dto.ErrorCode;
import com.example.SSH_Init.entity.User;
import com.example.SSH_Init.exception.BusinessException;
import com.example.SSH_Init.security.AuthUserDetail;
import com.example.SSH_Init.security.JwtProvider;
import com.example.SSH_Init.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    /**
     * Registration
     * POST: http://localhost:8080/signup
     * @param signupRequest
     * @return
     */
    @PostMapping("/signup")
    public BaseResponse<Object> signup(@Valid @RequestBody SignupRequest signupRequest) {
        // check if we already have this username
        User userByUsername = userService.findByUsername(signupRequest.getUsername());
        if (userByUsername != null) {
            throw new BusinessException(ErrorCode.USERNAME_ALREADY_EXISTS_ERROR);
        }

        // check if we already have this email
        User userByEmail = userService.findByEmail(signupRequest.getEmail());
        if (userByEmail != null) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS_ERROR);
        }

        // register
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        // Todo: Password should be encrypted
        user.setPassword(signupRequest.getPassword());
        user.setEmail(signupRequest.getEmail());
        // users are all buyers
        user.setRole(0);
        userService.add(user);
        return BaseResponse.success(null, "Register Successfully");
    }

    // LogIn
    // POST: http://localhost:8080/login
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication;

        //Try to authenticate the user using the username and password
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS_ERROR);
        }

        //Successfully authenticated user will be stored in the authUserDetail object
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal(); //getPrincipal() returns the user object

        //A token wil be created using the username/email/userId and permission
        String token = jwtProvider.createToken(authUserDetail);

        //Returns the token as a response to the frontend/postman
        return BaseResponse.success(token, "Welcome " + authUserDetail.getUsername());
    }
}
