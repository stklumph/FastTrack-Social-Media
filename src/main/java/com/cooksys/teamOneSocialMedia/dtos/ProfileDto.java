package com.cooksys.teamOneSocialMedia.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileDto {
	public String firstName;

	public String lastName;

	public String email;

	public String phone;
}
