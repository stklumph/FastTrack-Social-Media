package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.exceptions.NotFoundException;
import com.cooksys.teamOneSocialMedia.mappers.HashtagMapper;
import com.cooksys.teamOneSocialMedia.mappers.TweetMapper;
import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.repositories.TweetRepository;
import com.cooksys.teamOneSocialMedia.service.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

	@Override
	public List<TweetResponseDto> getAllTweetsWithHashtag(String label) {
		Optional<Hashtag> optionalTag = hashtagRepository.findByLabel(label);
		if(optionalTag.isEmpty()) {
			throw new NotFoundException("No hashtag found with label: " + label);
		}
		
		List<Tweet> all = tweetRepository.findByDeletedFalseOrderByPostedDesc();// findByDeletedFalse();
		List<Tweet> remove = new ArrayList<>();
		for (Tweet t : all) {
			if (!t.getHashtags().stream().anyMatch(o -> o.getLabel().equals(label))) {
				remove.add(t);
			}
		}
		all.removeAll(remove);
		return tweetMapper.entitiesToDtos(all);
	}
	// 1. #four
	// 2. #five #four

}
