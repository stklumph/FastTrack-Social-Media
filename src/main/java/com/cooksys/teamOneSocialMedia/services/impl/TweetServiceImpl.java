package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.mappers.TweetMapper;
import com.cooksys.teamOneSocialMedia.repositories.TweetRepository;
import com.cooksys.teamOneSocialMedia.service.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;
	@Override
	public List<TweetResponseDto> getAllTweets() {
		return tweetMapper.entitiesToDtos(tweetRepository.findByDeletedFalseOrderByPostedDesc());
	}

}
