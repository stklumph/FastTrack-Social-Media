package com.cooksys.teamOneSocialMedia.service;

import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
}
