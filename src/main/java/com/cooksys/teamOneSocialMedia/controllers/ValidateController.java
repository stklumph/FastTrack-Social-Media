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
	
	@GetMapping("/username/exists/@{username}")
	public boolean doesUsernameExist(@PathVariable String username) {
		return validateService.doesUsernameExist(username);
	}
}
