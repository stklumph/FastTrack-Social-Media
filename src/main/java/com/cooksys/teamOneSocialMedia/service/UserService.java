package com.cooksys.teamOneSocialMedia.service;

import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;


import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

   UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);

    UserResponseDto patchUser(UserRequestDto userRequestDto);

    UserResponseDto deleteUser(UserRequestDto userRequestDto);
}
