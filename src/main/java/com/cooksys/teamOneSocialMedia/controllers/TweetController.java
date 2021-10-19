package com.cooksys.teamOneSocialMedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public List<TweetResponseDto> getAllTweets(){
		return tweetService.getAllTweets();
	}
}
