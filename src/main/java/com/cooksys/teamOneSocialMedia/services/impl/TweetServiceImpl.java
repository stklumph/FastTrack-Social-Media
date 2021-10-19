package com.cooksys.teamOneSocialMedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
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
	
	private final HashtagMapper hashtagMapper;
	
	private final UserMapper userMapper;
	
	@Override
	public List<TweetResponseDto> getAllTweets() {
		return tweetMapper.entitiesToDtos(tweetRepository.findByDeletedFalseOrderByPostedDesc());
	}
	
	//helper method
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
		for(User u: userLikes) {
			if(u.isDeleted()) {
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
		for(User u: userMentions) {
			if(u.isDeleted()) {
				remove.add(u);
			}
		}
		userMentions.removeAll(remove);
		return userMapper.entitiesToDtos(userMentions);
	}

	public <T extends Deleted> List<T> filterDeleted(List<T> filter){
		List<T> remove = new ArrayList<>();
		for(T t: filter) {
			if(t.isDeleted()) {
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

}







