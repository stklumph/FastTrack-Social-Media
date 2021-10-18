package com.cooksys.teamOneSocialMedia.entities.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class Profile {
	
	private String firstName;
	
	private String lastName;
	
	@Column(nullable=false)
	private String email;
	
	private String phone;
	
}
