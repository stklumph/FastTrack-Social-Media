package com.cooksys.teamOneSocialMedia.service;

public interface ValidateService {

	boolean doesUsernameExist(String username);

	boolean doesHashtagExist(String label);

}
