package com.cooksys.teamOneSocialMedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = -4045037615372804757L;

	private String message;
}	
