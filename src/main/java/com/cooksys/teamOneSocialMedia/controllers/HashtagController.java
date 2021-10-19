package com.cooksys.teamOneSocialMedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.service.HashtagService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {
	private final HashtagService hashtagService;
	//Retrieves all hashtags tracked by the database.
	@GetMapping
	public List<HashtagDto> getAllHashtags(){
		return hashtagService.getAllHashtags();
	}
	
	//Retrieves all (non-deleted) tweets tagged with the given hashtag label
	@GetMapping("{label}")
	public List<TweetResponseDto> getAllHashtagsWithLabel(@PathVariable String label){
		return hashtagService.getAllHashtagsWithLabel(label);
	}
	
}
