package com.cooksys.teamOneSocialMedia.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetRequestDto {
	public String content;
	
	public CredentialsDto credentials;
}
