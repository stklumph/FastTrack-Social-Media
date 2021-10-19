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
    @GetMapping ("/@{username}") //Retrieves a user with the given username. If no such user exists or is deleted, an error should be sent in lieu of a response.
    public UserResponseDto getUser(@PathVariable String username){
        return userService.getUser(username);
    }
}
