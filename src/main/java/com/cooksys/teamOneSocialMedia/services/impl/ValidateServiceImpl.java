package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.exceptions.NotFoundException;
import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.repositories.UserRepository;
import com.cooksys.teamOneSocialMedia.service.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;
	private final HashtagRepository hashtagRepository;

	// These two methods will likely be repurposed into the UserServiceImpl
	private User getUserById(long id) {
		Optional<User> optionalUser = userRepository.findByIdAndDeletedFalse(id);
		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user found with id: " + id);
		}
		return optionalUser.get();
	}

	private User getUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user found with username: " + username);
		}
		return optionalUser.get();
	}

	@Override
	public boolean doesHashtagExist(String label) {
		return hashtagRepository.findByLabel(label).isPresent();
	}

	@Override
	public boolean doesUsernameExist(String username) {
		return userRepository.findByCredentialsUsernameAndDeletedFalse(username).isPresent();
	}

}
