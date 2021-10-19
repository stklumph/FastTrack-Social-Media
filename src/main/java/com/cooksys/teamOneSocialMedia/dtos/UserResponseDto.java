package com.cooksys.teamOneSocialMedia.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	public String username;

	public ProfileDto profile;

	public Timestamp joined;
}
