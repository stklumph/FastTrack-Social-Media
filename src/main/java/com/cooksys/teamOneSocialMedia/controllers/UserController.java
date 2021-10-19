package com.cooksys.teamOneSocialMedia.controllers;

import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping//Retrieves all active (non-deleted) users as an array.
    public List<UserResponseDto> getAllUsers(){

        return userService.getAllUsers();
    }

}
