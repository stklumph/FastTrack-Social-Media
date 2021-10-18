package com.cooksys.teamOneSocialMedia.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
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
	@Column(nullable=false)
	private Credentials credentials;
	
	@Embedded
	@Column(nullable=false)
	private Profile profile;
	
	//Adding this line might cause a problem if a Timestamp is not sent as part of the request to the database, not sure How to handle this
	@Column(nullable=false, updatable=false)	
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
