package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Deleted;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import com.cooksys.teamOneSocialMedia.exceptions.BadRequestException;
import com.cooksys.teamOneSocialMedia.exceptions.NotFoundException;
import com.cooksys.teamOneSocialMedia.mappers.HashtagMapper;
import com.cooksys.teamOneSocialMedia.mappers.TweetMapper;
import com.cooksys.teamOneSocialMedia.mappers.UserMapper;
import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.repositories.TweetRepository;
import com.cooksys.teamOneSocialMedia.repositories.UserRepository;
import com.cooksys.teamOneSocialMedia.service.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;

	private final HashtagMapper hashtagMapper;
	private final HashtagRepository hashtagRepository;

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	@Override
	public List<TweetResponseDto> getAllTweets() {
		return tweetMapper.entitiesToDtos(tweetRepository.findByDeletedFalseOrderByPostedDesc());
	}

	// helper method
	private Tweet getTweet(Integer id) {
		Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(id);
		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("No tweet found with id: " + id);
		}
		return optionalTweet.get();
	}

	@Override
	public TweetResponseDto getTweetById(Integer id) {
		return tweetMapper.entityToDto(getTweet(id));
	}

	@Override
	public List<HashtagDto> getTagsByTweetId(Integer id) {
		Tweet tweet = getTweet(id);
		return hashtagMapper.entitiesToDtos(tweet.getHashtags());
	}

	@Override
	public List<UserResponseDto> getTweetLikes(Integer id) {
		Tweet tweet = getTweet(id);
		List<User> userLikes = tweet.getLikes();
		List<User> remove = new ArrayList<>();
		for (User u : userLikes) {
			if (u.isDeleted()) {
				remove.add(u);
			}
		}
		userLikes.removeAll(remove);
		return userMapper.entitiesToDtos(userLikes);
	}

	@Override
	public List<UserResponseDto> getTweetMentions(Integer id) {
		Tweet tweet = getTweet(id);
		List<User> userMentions = tweet.getUsersMentioned();
		List<User> remove = new ArrayList<>();
		for (User u : userMentions) {
			if (u.isDeleted()) {
				remove.add(u);
			}
		}
		userMentions.removeAll(remove);
		return userMapper.entitiesToDtos(userMentions);

	}

	public <T extends Deleted> List<T> filterDeleted(List<T> filter) {
		List<T> remove = new ArrayList<>();
		for (T t : filter) {
			if (t.isDeleted()) {
				remove.add(t);
			}
		}
		filter.removeAll(remove);
		return filter;
	}

	@Override
	public List<TweetResponseDto> getTweetReplies(Integer id) {
		Tweet tweet = getTweet(id);
		List<Tweet> replies = filterDeleted(tweet.getReplies());
		return tweetMapper.entitiesToDtos(replies);
	}

	private List<String> parse(String content, String regEx) {
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(content);
		List<String> mentions = new ArrayList<>();
		while (matcher.find()) {
			// this adds the parsed string, without the "@" or the "#"
			mentions.add(matcher.group().substring(1));
		}

		return mentions;
	}

	private List<User> getMentionedUsers(List<String> usernames) {
		List<User> users = new ArrayList<>();
		for (String u : usernames) {
			Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(u);
			if (optionalUser.isPresent()) {
				users.add(optionalUser.get());
			}
		}
		return users;
	}

	private List<Hashtag> getTags(List<String> tags) {
		List<Hashtag> users = new ArrayList<>();
		for (String u : tags) {
			Optional<Hashtag> optionalHashtag = hashtagRepository.findByLabel(u);
			if (optionalHashtag.isPresent()) {
				users.add(optionalHashtag.get());
			}
		}
		return users;
	}

	private User getUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user found with username: " + username);
		}
		return optionalUser.get();
	}

//	private void validateUserCredentials(Credentials credentials) {
//		User user = getUserByUsername(credentials.getUsername());
//		if (!user.getCredentials().equals(credentials)) {
//			throw new BadRequestException("Credentials invalid");
//		}
//	}
	
	 private void validateUserCredentials(User user, Credentials credentials) {
	        if(!user.getCredentials().equals(credentials)) {
	            throw new BadRequestException("Credentials invalid");
	        }
	    }

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
		Tweet newTweet = tweetMapper.requestDtoToEntity(tweetRequestDto);
		Credentials credentials = new Credentials(tweetRequestDto.getCredentials().getUsername(),
				tweetRequestDto.getCredentials().getPassword());
		User user = getUserByUsername(credentials.getUsername());
		validateUserCredentials(user, credentials);
		newTweet.setAuthor(user);
		String content = tweetRequestDto.getContent();
		final String ampersandRegEx = "^@";
		final String tagRegEx = "^#";
		newTweet.setUsersMentioned(getMentionedUsers(parse(content, ampersandRegEx)));
		newTweet.setHashtags(getTags(parse(content, tagRegEx)));

		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(newTweet));
	}

}
