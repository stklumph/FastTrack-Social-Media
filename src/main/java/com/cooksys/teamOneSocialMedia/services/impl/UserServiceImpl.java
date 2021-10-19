package com.cooksys.teamOneSocialMedia.services.impl;


import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.exceptions.BadRequestException;
import com.cooksys.teamOneSocialMedia.mappers.UserMapper;
import com.cooksys.teamOneSocialMedia.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entitiesToDtos(userRepository.findByDeletedFalse());
    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        String username = userRequestDto.getCredentials().getUsername();
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.isDeleted()) {
                if (user.getCredentials().getPassword().equals(userRequestDto.getCredentials().getPassword())) {
                    user.setDeleted(false);
                    userRepository.saveAndFlush(user);
                    return userMapper.entityToDto(userMapper.requestDtoToEntity(userRequestDto));
                }
                else {
                    throw new BadRequestException("username : " + username + " is already taken");
                }
            }
            else {
                throw new BadRequestException("username : " + username + " is already taken");
            }
        }//create user
        else {return userMapper.entityToDto(userRepository.saveAndFlush(userMapper.requestDtoToEntity(userRequestDto)));
        }

    }

}
