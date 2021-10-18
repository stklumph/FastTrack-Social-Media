package com.cooksys.teamOneSocialMedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException  {/**
	 * 
	 */
	private static final long serialVersionUID = 1833246690139246713L;

	private String message;
}
