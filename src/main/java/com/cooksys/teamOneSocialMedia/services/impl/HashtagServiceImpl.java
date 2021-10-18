package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.service.HashtagService;
import com.cooksys.teamOneSocialMedia.mappers.HashtagMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService{
	
	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	
	
	@Override
	public List<Hashtag> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

}
