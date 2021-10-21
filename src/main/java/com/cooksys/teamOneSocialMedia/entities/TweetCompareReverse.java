package com.cooksys.teamOneSocialMedia.entities;

import java.util.Comparator;

public class TweetCompareReverse implements Comparator<Tweet>{

	@Override
	public int compare(Tweet t1, Tweet t2) {
		return (-1*(t1.getPosted().compareTo(t2.getPosted())));
	}
	

}
