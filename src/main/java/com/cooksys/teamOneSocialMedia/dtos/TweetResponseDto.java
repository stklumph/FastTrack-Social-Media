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

	public User author;

	public Timestamp posted;

	public String content;

	public Tweet inReplyTo;

	public Tweet repostOf;
}