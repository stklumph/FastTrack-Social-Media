package com.cooksys.teamOneSocialMedia.service;


import java.util.List;

import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;

import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;




public interface UserService {
    List<UserResponseDto> getAllUsers();

   UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);

    UserResponseDto patchUser(UserRequestDto userRequestDto);

    UserResponseDto deleteUser(UserRequestDto userRequestDto);

    void followUser(String username, CredentialsDto credentialsDto);

    void unfollowUser(String username, CredentialsDto credentialsDto);
}
