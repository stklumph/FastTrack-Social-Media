package com.cooksys.teamOneSocialMedia.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContextDto {
	public TweetResponseDto target;

	public List<TweetResponseDto> before;

	public List<TweetResponseDto> after;
}
