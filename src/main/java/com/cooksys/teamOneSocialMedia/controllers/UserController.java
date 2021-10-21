package com.cooksys.teamOneSocialMedia.controllers;

import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;
import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.dtos.TweetRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.service.TweetService;
import com.cooksys.teamOneSocialMedia.service.UserService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TweetService tweetService;


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
    @PostMapping ("/@{username}/follow") //Subscribes the user whose credentials are provided by the request body to the user whose username is given in the url. If there is already a following relationship between the two users, no such followable user exists (deleted or never created), or the credentials provided do not match an active user in the database, an error should be sent as a response. If successful, no data is sent.
    public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto){
        userService.followUser(username, credentialsDto);
    }
    @PostMapping ("/@{username}/unfollow") //Subscribes the user whose credentials are provided by the request body to the user whose username is given in the url. If there is already a following relationship between the two users, no such followable user exists (deleted or never created), or the credentials provided do not match an active user in the database, an error should be sent as a response. If successful, no data is sent.
    public void unfollowUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto){
        userService.unfollowUser(username, credentialsDto);
    }
    @GetMapping ("/@{username}/tweets") // Retrieves all (non-deleted) tweets authored by the user with the given username. This includes simple tweets, reposts, and replies. The tweets should appear in reverse-chronological order. If no active user with that username exists (deleted or never created), an error should be sent in lieu of a response.
    public List<TweetResponseDto> getUserTweets(@PathVariable String username){
        return tweetService.getAllTweetsByUser(username);
    }
    
    //Retrieves all (non-deleted) tweets authored by the user with the given username,
    //as well as all (non-deleted) tweets authored by users the given user is following
    @GetMapping("@{username}/feed")
    public List<TweetResponseDto> getUserFeed(@PathVariable String username){
    	return tweetService.getUserFeed(username);
    }
    

}








