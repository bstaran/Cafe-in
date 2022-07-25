package com.team2.cafein.service;

import com.team2.cafein.dto.ResponseMessageDto;
import com.team2.cafein.dto.SignupRequestDto;
import com.team2.cafein.model.User;
import com.team2.cafein.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseMessageDto registerUser(SignupRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setStatus(true);
        responseMessageDto.setMessage("회원가입 성공");
        return responseMessageDto;
    }

    public ResponseMessageDto login(SignupRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail());

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();

        if (user==null||!(user.getPassword().equals(requestDto.getPassword()))) {
            throw new IllegalArgumentException("아이디나 비밀번호 확인");
        }

        responseMessageDto.setStatus(true);
        responseMessageDto.setMessage("로그인 성공");
        return responseMessageDto;
    }
}
