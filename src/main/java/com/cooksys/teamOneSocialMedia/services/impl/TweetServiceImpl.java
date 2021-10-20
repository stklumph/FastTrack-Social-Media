package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.ContextDto;
import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Deleted;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.exceptions.NotFoundException;
import com.cooksys.teamOneSocialMedia.mappers.HashtagMapper;
import com.cooksys.teamOneSocialMedia.mappers.TweetMapper;
import com.cooksys.teamOneSocialMedia.mappers.UserMapper;
import com.cooksys.teamOneSocialMedia.repositories.TweetRepository;
import com.cooksys.teamOneSocialMedia.service.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;

	private final String ampersand = "^@";
	private final String hashtag = "^#";

	private final HashtagMapper hashtagMapper;

	private final UserMapper userMapper;

	@Override
	public List<TweetResponseDto> getAllTweets() {
		return tweetMapper.entitiesToDtos(tweetRepository.findByDeletedFalseOrderByPostedDesc());
	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
		Pattern pattern = Pattern.compile(ampersand);
		Matcher matcher = pattern.matcher(tweetRequestDto.getContent());

		return null;
	}

	// helper method
	private Tweet getTweet(Integer id) {
		Optional<Tweet> optionalTweet = tweetRepository.findByIdAndDeletedFalse(id);
		if (optionalTweet.isEmpty()) {
			throw new NotFoundException("No tweet found with id: " + id);
		}
		return optionalTweet.get();
	}
	//helper method
	public <T extends Deleted> List<T> filterDeleted(List<T> filter){
		if(filter.isEmpty()) {
			return filter;
		}
		List<T> remove = new ArrayList<>();
		for(T t: filter) {
			if(t == null) {
				continue;
			}
			if(t.isDeleted()) {
				remove.add(t);
			}
		}
		filter.removeAll(remove);
		return filter;
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
		List<User> userLikes = filterDeleted(tweet.getLikes());
		return userMapper.entitiesToDtos(userLikes);
	}

	@Override
	public List<UserResponseDto> getTweetMentions(Integer id) {
		Tweet tweet = getTweet(id);
		List<User> userMentions = filterDeleted(tweet.getUsersMentioned());
		return userMapper.entitiesToDtos(userMentions);
	}
	
	@Override
	public List<TweetResponseDto> getTweetReplies(Integer id) {
		Tweet tweet = getTweet(id);
		List<Tweet> replies = filterDeleted(tweet.getReplies());
		return tweetMapper.entitiesToDtos(replies);
	}

	@Override
	public ContextDto getTweetContext(Integer id) {
		Tweet tweet = getTweet(id);
		
		ContextDto context = new ContextDto();
		context.setTarget(tweetMapper.entityToDto(tweet));
		List<Tweet> before = new ArrayList<>();
		Tweet current = tweet;
		while(current.getInReplyTo() != null) {
			current = current.getInReplyTo();
			before.add(0, current.getInReplyTo());
		}
		
		context.setBefore(tweetMapper.entitiesToDtos(filterDeleted(before)));
		
		Stack<Tweet> toVisit = new Stack<>();
		List<Tweet> after = new ArrayList<>();
		current = tweet; //reset
		if(!current.getReplies().isEmpty()) {
			for(Tweet t: current.getReplies()) {
				toVisit.push(t);
			}
		}
		while (!toVisit.isEmpty()) {
			current = toVisit.pop();
			after.add(current);
			if(current.getReplies().isEmpty()) {
				continue;
			}
			else {
				for(Tweet t: current.getReplies()) {
					toVisit.push(t);
				}
			}
		}
		Collections.sort(filterDeleted(after));
		context.setAfter(tweetMapper.entitiesToDtos(after));
		
		return context;
	}

	@Override
	public List<TweetResponseDto> getTweetReposts(Integer id) {
		Tweet tweet = getTweet(id);
		return tweetMapper.entitiesToDtos(filterDeleted(tweet.getReposts()));
	}

}










