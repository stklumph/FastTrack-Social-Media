package com.cooksys.teamOneSocialMedia.service;

import java.util.List;

import com.cooksys.teamOneSocialMedia.dtos.TweetRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.ContextDto;
import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;
import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;

public interface TweetService {

	List<TweetResponseDto> getAllTweets();

	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

	TweetResponseDto getTweetById(Integer id);

	List<HashtagDto> getTagsByTweetId(Integer id);

	List<UserResponseDto> getTweetLikes(Integer id);

	List<UserResponseDto> getTweetMentions(Integer id);

	List<TweetResponseDto> getTweetReplies(Integer id);

	ContextDto getTweetContext(Integer id);

	List<TweetResponseDto> getTweetReposts(Integer id);

	TweetResponseDto deleteTweetById(Integer id, CredentialsDto credentialsDto);

	List<TweetResponseDto> getAllTweetsByUser(String username);

	void postLike(Integer id, CredentialsDto credentialsDto);

	TweetResponseDto repostTweet(Integer id, CredentialsDto credentialsDto);

}
