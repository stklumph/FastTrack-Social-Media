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

    @PatchMapping ("/@{username}") //Updates the profile of a user with the given username. If no such user exists, the user is deleted, or the provided credentials do not match the user, an error should be sent in lieu of a response. In the case of a successful update, the returned user should contain the updated data.

    public UserResponseDto patchUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.patchUser(userRequestDto);
    }
    @DeleteMapping ("/@{username}") // "Deletes" a user with the given username. If no such user exists or the provided credentials do not match the user, an error should be sent in lieu of a response. If a user is successfully "deleted", the response should contain the user data prior to deletion.
    public UserResponseDto deleteUser(@RequestBody UserRequestDto userRequestDto){
        return userService.deleteUser(userRequestDto);
    }



}
