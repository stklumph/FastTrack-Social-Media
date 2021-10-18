package com.cooksys.teamOneSocialMedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.service.HashtagService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {
	private final HashtagService hashtagService;
	//Retrieves all hashtags tracked by the database.
	@GetMapping
	public List<Hashtag> getAllHashtags(){
		return hashtagService.getAllHashtags();
	}
}
