package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Deleted;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.TweetCompareReverse;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import com.cooksys.teamOneSocialMedia.exceptions.BadRequestException;
import com.cooksys.teamOneSocialMedia.exceptions.NotFoundException;
import com.cooksys.teamOneSocialMedia.mappers.CredentialsMapper;
import com.cooksys.teamOneSocialMedia.mappers.TweetMapper;
import com.cooksys.teamOneSocialMedia.mappers.UserMapper;
import com.cooksys.teamOneSocialMedia.repositories.UserRepository;
import com.cooksys.teamOneSocialMedia.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final TweetMapper tweetMapper;
	private final CredentialsMapper credentialsMapper;

	@Override
	public List<UserResponseDto> getAllUsers() {
		return userMapper.entitiesToDtos(userRepository.findByDeletedFalse());
	}

	@Override
	public <T extends Deleted> List<T> filterDeleted(List<T> filter) {
		if (filter.isEmpty()) {
			return filter;
		}
		List<T> remove = new ArrayList<>();
		for (T t : filter) {
			if (t == null) {
				continue;
			}
			if (t.isDeleted()) {
				remove.add(t);
			}
		}
		filter.removeAll(remove);
		return filter;
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		String username = userRequestDto.getCredentials().getUsername();
		Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (user.isDeleted()) {
				if (user.getCredentials().getPassword().equals(userRequestDto.getCredentials().getPassword())) {
					user.setDeleted(false);
					userRepository.saveAndFlush(user);
					return userMapper.entityToDto(userMapper.requestDtoToEntity(userRequestDto));
				} else {
					throw new BadRequestException("username : " + username + " is already taken");
				}
			} else {
				throw new BadRequestException("username : " + username + " is already taken");
			}
		} // create user
		else {
			return userMapper.entityToDto(userRepository.saveAndFlush(userMapper.requestDtoToEntity(userRequestDto)));
		}

	}

	public User getUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user found with username: " + username);
		}
		return optionalUser.get();
	}

	@Override
	public UserResponseDto getUser(String username) {
		return userMapper.entityToDto(getUserByUsername(username));
	}

	public void validateUserCredentials(User user, Credentials credentials) {
		if (!user.getCredentials().equals(credentials)) {
			throw new BadRequestException("Credentials invalid");
		}
	}

	@Override
	public UserResponseDto patchUser(UserRequestDto userRequestDto) {
		User user = getUserByUsername(userRequestDto.getCredentials().getUsername());
		User userCheck = userMapper.requestDtoToEntity(userRequestDto);
		validateUserCredentials(user, userCheck.getCredentials());
		user.setProfile(userCheck.getProfile());
		return userMapper.entityToDto(userRepository.saveAndFlush(user));
	}

	@Override
	public UserResponseDto deleteUser(UserRequestDto userRequestDto) {
		User user = getUserByUsername(userRequestDto.getCredentials().getUsername());
		User userCheck = userMapper.requestDtoToEntity(userRequestDto);
		validateUserCredentials(user, userCheck.getCredentials());
		user.setDeleted(true);
		return userMapper.entityToDto(userRepository.saveAndFlush(user));
	}

	@Override
	public void followUser(String username, CredentialsDto credentialsDto) {
		User user = getUserByUsername(credentialsDto.getUsername());
		validateUserCredentials(user, credentialsMapper.dtoToEntity(credentialsDto));
		User userToFollow = getUserByUsername(username);
		userToFollow.userFollowing(user);
		userRepository.saveAndFlush(userToFollow);
	}

	@Override
	public void unfollowUser(String username, CredentialsDto credentialsDto) {
		User user = getUserByUsername(credentialsDto.getUsername());
		validateUserCredentials(user, credentialsMapper.dtoToEntity(credentialsDto));
		User userToFollow = getUserByUsername(username);
		userToFollow.userUnfollowing(user);
		userRepository.saveAndFlush(userToFollow);
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {
		return userMapper.entitiesToDtos(filterDeleted(getUserByUsername(username).getFollowers()));
	}

	@Override
	public List<UserResponseDto> getFollowing(String username) {
		return userMapper.entitiesToDtos(filterDeleted(getUserByUsername(username).getFollowing()));
	}

	@Override
	public List<TweetResponseDto> getMentions(String username) {
		TweetCompareReverse tcr = new TweetCompareReverse();
		List<Tweet> mentions = getUserByUsername(username).getMentions();
		mentions.sort(tcr);
		return tweetMapper.entitiesToDtos(filterDeleted(mentions));
	}

	@Override
	public List<TweetResponseDto> getAllTweetsByUser(String username) {
		List<Tweet> tweets = getUserByUsername(username).getTweets();
		TweetCompareReverse tcr = new TweetCompareReverse();
		tweets.sort(tcr);
		return tweetMapper.entitiesToDtos(filterDeleted(tweets));
	}

}
