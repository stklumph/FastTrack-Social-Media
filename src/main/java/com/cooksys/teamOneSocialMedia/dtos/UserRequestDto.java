package com.cooksys.teamOneSocialMedia.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {
	public CredentialsDto credentials;
	
	public ProfileDto profile;
}
