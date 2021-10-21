package com.cooksys.teamOneSocialMedia.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.repositories.UserRepository;
import com.cooksys.teamOneSocialMedia.service.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;
	private final HashtagRepository hashtagRepository;

	@Override
	public boolean doesHashtagExist(String label) {
		return hashtagRepository.findByLabel(label).isPresent();
	}

	@Override
	public boolean doesUsernameExist(String username) {
		return userRepository.findByCredentialsUsernameAndDeletedFalse(username).isPresent();
	}

}
