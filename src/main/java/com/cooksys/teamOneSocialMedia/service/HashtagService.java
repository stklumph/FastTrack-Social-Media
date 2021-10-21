package com.cooksys.teamOneSocialMedia.service;

import java.util.List;

import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;

public interface HashtagService {

	List<HashtagDto> getAllHashtags();

	List<TweetResponseDto> getAllHashtagsWithLabel(String label);

}
