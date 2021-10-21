package com.cooksys.teamOneSocialMedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.teamOneSocialMedia.dtos.ContextDto;
import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;
import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.service.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	private final TweetService tweetService;

	// Retrieves all (non-deleted) tweets. The tweets should appear in
	// reverse-chronological order
	@GetMapping
	public List<TweetResponseDto> getAllTweets() {
		return tweetService.getAllTweets();
	}

	// Retrieves a tweet with a given id. If no such tweet exists, or the given
	// tweet is deleted,
	// an error should be sent in lieu of a response
	@GetMapping("/{id}")
	public TweetResponseDto getTweetById(@PathVariable Integer id) {
		return tweetService.getTweetById(id);
	}

	// Retrieves the tags associated with the tweet with the given id. If that tweet
	// is deleted
	// or otherwise doesn't exist, an error should be sent in lieu of a response.
	@GetMapping("/{id}/tags")
	public List<HashtagDto> getTagsByTweetId(@PathVariable Integer id) {
		return tweetService.getTagsByTweetId(id);
	}

	// Retrieves the active users who have liked the tweet with the given id. If
	// that tweet is
	// deleted or otherwise doesn't exist, an error should be sent in lieu of a
	// response.
	@GetMapping("/{id}/likes")
	public List<UserResponseDto> getTweetLikes(@PathVariable Integer id) {
		return tweetService.getTweetLikes(id);
	}

	// Retrieves the users mentioned in the tweet with the given id. If that tweet
	// is deleted
	// or otherwise doesn't exist, an error should be sent in lieu of a response.
	@GetMapping("/{id}/mentions")
	public List<UserResponseDto> getTweetMentions(@PathVariable Integer id) {
		return tweetService.getTweetMentions(id);
	}

	// Retrieves the direct replies to the tweet with the given id. If that tweet is
	// deleted
	// or otherwise doesn't exist, an error should be sent in lieu of a response.
	@GetMapping("/{id}/replies")
	public List<TweetResponseDto> getTweetReplies(@PathVariable Integer id) {
		return tweetService.getTweetReplies(id);
	}
	
	//Retrieves the context of the tweet with the given id. If that tweet is deleted
	//or otherwise doesn't exist, an error should be sent in lieu of a response.
	@GetMapping("/{id}/context")
	public ContextDto getTweetContext(@PathVariable Integer id) {
		return tweetService.getTweetContext(id);
	}
	
	//Retrieves the direct reposts of the tweet with the given id. If that tweet is 
	//deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
	@GetMapping("/{id}/reposts")
	public List<TweetResponseDto> getTweetReposts(@PathVariable Integer id){
		return tweetService.getTweetReposts(id);
	}
	
	//"Deletes" the tweet with the given id
	@DeleteMapping("/{id}")
	public TweetResponseDto deleteTweetById(@PathVariable Integer id, @RequestBody CredentialsDto credentialsDto) {
		return tweetService.deleteTweetById(id, credentialsDto);
	}

	// Creates a tweet from a tweetRequestDto
	@PostMapping
	public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
		return tweetService.createTweet(tweetRequestDto);
	}

}











