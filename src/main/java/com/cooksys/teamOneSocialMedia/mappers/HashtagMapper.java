package com.cooksys.teamOneSocialMedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.teamOneSocialMedia.dtos.HashtagDto;
import com.cooksys.teamOneSocialMedia.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

	List<HashtagDto> entitiesToDtos(List<Hashtag> entities);
}
