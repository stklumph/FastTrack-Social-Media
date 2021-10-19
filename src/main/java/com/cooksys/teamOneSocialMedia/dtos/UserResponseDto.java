package com.cooksys.teamOneSocialMedia.dtos;

import java.sql.Timestamp;

import javax.persistence.Embedded;

import com.cooksys.teamOneSocialMedia.entities.embeddables.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	public String username;

	public ProfileDto profile;

	public Timestamp joined;
}
