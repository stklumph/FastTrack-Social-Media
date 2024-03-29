package com.cooksys.teamOneSocialMedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.teamOneSocialMedia.dtos.UserRequestDto;
import com.cooksys.teamOneSocialMedia.dtos.UserResponseDto;
import com.cooksys.teamOneSocialMedia.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	List<UserResponseDto> entitiesToDtos(List<User> entities);

	@Mapping(target = "username", source = "credentials.username")
	UserResponseDto entityToDto(User user);

	User requestDtoToEntity(UserRequestDto userRequestDto);
}
