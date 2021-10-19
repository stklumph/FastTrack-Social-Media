package com.cooksys.teamOneSocialMedia.dtos;

import java.sql.Timestamp;

import javax.persistence.Embedded;

import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	public String username;
	
	@Embedded
	public Profile profile;

	public Timestamp joined;
}
