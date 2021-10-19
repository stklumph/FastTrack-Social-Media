package com.cooksys.teamOneSocialMedia.mappers;

import com.cooksys.teamOneSocialMedia.dtos.TweetResponseDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserResponseDto> entitiesToDtos(List<User> entities);

    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToDto(User user);
}
