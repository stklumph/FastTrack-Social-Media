package com.cooksys.teamOneSocialMedia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.teamOneSocialMedia.service.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

	private final ValidateService validateService;

	@GetMapping("/tag/exists/{label}")
	public boolean doesHashtagExist(@PathVariable String label) {
		return validateService.doesHashtagExist(label);
	}

	@GetMapping("/username/exists/@{username}")
	public boolean doesUsernameExist(@PathVariable String username) {
		return validateService.doesUsernameExist(username);
	}

	// I'm not entirely sure what the difference between a username existing and
	// being available is. I assume that a username being available indicates there
	// are no active users (deleted or not existing) with that username, but I'm not
	// sure if there are other qualifiers
	@GetMapping("/username/available/@{username}")
	public boolean isUsernameAvailable(@PathVariable String username) {
		return !validateService.doesUsernameExist(username);
	}

}
