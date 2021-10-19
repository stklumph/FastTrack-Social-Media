package com.cooksys.teamOneSocialMedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Tweet;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {
	List<TweetResponseDto> entitiesToDtos( List<Tweet> entities);
	
}
