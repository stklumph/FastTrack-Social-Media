package com.cooksys.teamOneSocialMedia.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	private Credentials credentials;
	
	@Embedded
	private Profile profile;
	
	private Timestamp joined;
	
	private boolean deleted;
	
	@OneToMany(mappedBy = "author")
	private List<Tweet> tweets;
	
	@ManyToMany(mappedBy = "likes")
	private List<Tweet> likedTweets;
	
	@ManyToMany
	@JoinTable
	private List<User> follwers;
	
	@ManyToMany(mappedBy = "followers")
	private List<User> follwing;
	
	@ManyToMany(mappedBy = "usersMentioned")
	private List<Tweet> mentions;
}
