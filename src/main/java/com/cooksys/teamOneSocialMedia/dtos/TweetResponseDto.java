package com.cooksys.teamOneSocialMedia.dtos;

import java.sql.Timestamp;

import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {
	public Integer Id;

	public UserResponseDto author;

	public Timestamp posted;

	public String content;

	public TweetResponseDto inReplyTo;

	public TweetResponseDto repostOf;
}
