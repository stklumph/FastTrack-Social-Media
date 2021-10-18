package com.cooksys.teamOneSocialMedia.service;

import java.util.List;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;

public interface HashtagService {

	List<Hashtag> getAllHashtags();

	List<TweetResponseDto> getAllHashtagsWithLabel(String label);

}
