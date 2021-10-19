package com.cooksys.teamOneSocialMedia.service;

import java.util.List;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;

public interface TweetService {

	List<TweetResponseDto> getAllTweets();

	TweetResponseDto getTweetById(Integer id);

}
