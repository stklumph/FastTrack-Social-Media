package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.repositories.TweetRepository;
import com.cooksys.teamOneSocialMedia.service.HashtagService;
import com.cooksys.teamOneSocialMedia.mappers.HashtagMapper;
import com.cooksys.teamOneSocialMedia.mappers.TweetMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService{
	
	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	
	@Override
	public List<Hashtag> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}


	@Override
	public List<TweetResponseDto> getAllHashtagsWithLabel(String label) {
		List<Tweet> all = tweetRepository.findByDeletedFalse();
		for(Tweet t: all) {
			if(!t.getHashtags().stream().anyMatch(o -> o.getLabel().equals(label))) {
				all.remove(t);
			}
		}
		
		return tweetMapper.entitiesToDtos(all);
	}

}
