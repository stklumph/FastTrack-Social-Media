package com.cooksys.teamOneSocialMedia.entities.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credentials {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
}
