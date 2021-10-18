package com.cooksys.teamOneSocialMedia.dtos;

import java.util.List;

import com.cooksys.teamOneSocialMedia.entities.Tweet;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContextDto {
	public Tweet target;

	public List<Tweet> before;

	public List<Tweet> after;
}