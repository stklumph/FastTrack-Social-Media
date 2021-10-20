package com.cooksys.teamOneSocialMedia.service;


import java.util.List;

import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;

import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;



public interface UserService {
    List<UserResponseDto> getAllUsers();

   UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(String username);

    UserResponseDto patchUser(UserRequestDto userRequestDto);

    UserResponseDto deleteUser(UserRequestDto userRequestDto);

	User getUserByUsername(String username);

	void validateUserCredentials(User user, Credentials credentials);

    void followUser(String username, CredentialsDto credentialsDto);

    void unfollowUser(String username, CredentialsDto credentialsDto);
}
