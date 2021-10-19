package com.cooksys.teamOneSocialMedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.service.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	private final TweetService tweetService;
	
	//Retrieves all (non-deleted) tweets. The tweets should appear in reverse-chronological order
	@GetMapping
	public List<TweetResponseDto> getAllTweets(){
		return tweetService.getAllTweets();
	}
	
	//Retrieves a tweet with a given id. If no such tweet exists, or the given tweet is deleted,
	//an error should be sent in lieu of a response
	@GetMapping("/{id}")
	public TweetResponseDto getTweetById(@PathVariable Integer id) {
		return tweetService.getTweetById(id);
	}
	
	
}
