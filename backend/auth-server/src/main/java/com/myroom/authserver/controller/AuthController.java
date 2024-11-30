package com.myroom.authserver.controller;

import com.myroom.authserver.api.model.UserResponseModel;
import com.myroom.authserver.api.model.VerifyTokenResponseModel;
import com.myroom.authserver.api.resource.AuthResource;
import com.myroom.authserver.data.mapper.UserMapper;
import com.myroom.authserver.data.model.FirebaseUserModel;
import com.myroom.authserver.usecase.AuthService;
import com.myroom.authserver.usecase.UserService;
import com.myroom.authserver.usecase.impl.firebase.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@Slf4j
public class AuthController implements AuthResource {

    @Autowired
    AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseEntity<VerifyTokenResponseModel> verifyToken(HttpServletRequest httpServletRequest) {
        log.info("Received verify token request for {}", httpServletRequest);
        VerifyTokenResponseModel verifyTokenResponse = authService.verifyToken(httpServletRequest);
        log.info("verified the token {}", verifyTokenResponse);
        return new  ResponseEntity<>(verifyTokenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseModel> getUserDetails(HttpServletRequest httpServletRequest) {
        Optional<FirebaseUserModel> userModel = userService.getUserByBearerToken(securityService.getBearerToken(httpServletRequest));
        if(userModel.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toUserResponseModel(userModel.get()), HttpStatus.OK);
    }
}