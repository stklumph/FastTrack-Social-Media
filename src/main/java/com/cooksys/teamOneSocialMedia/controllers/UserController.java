package com.cooksys.teamOneSocialMedia.controllers;

import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.service.UserService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;



    @GetMapping//Retrieves all active (non-deleted) users as an array.
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping// create or reactivate user
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }
}
