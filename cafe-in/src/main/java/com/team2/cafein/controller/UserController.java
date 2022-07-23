package com.team2.cafein.controller;

import com.team2.cafein.dto.ResponseMessageDto;
import com.team2.cafein.dto.SignupRequestDto;
import com.team2.cafein.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public ResponseMessageDto signup(@RequestBody SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    @PostMapping("/user/login")
    public ResponseMessageDto login(@RequestBody SignupRequestDto requestDto) {
        return userService.login(requestDto);
    }
}
