package com.cooksys.teamOneSocialMedia.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet implements Deleted, Comparable<Tweet>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@Column(nullable = false, updatable = false)
	private Timestamp posted = new Timestamp(System.currentTimeMillis());

	private boolean deleted;

	private String content;

	@ManyToOne
	@JoinColumn(name = "in_reply_to_ID")
	private Tweet inReplyTo;

	@OneToMany(mappedBy="inReplyTo")	
	private List<Tweet> replies;

	@ManyToOne
	@JoinColumn(name = "repost_of_ID")
	private Tweet repostOf;

	@OneToMany(mappedBy = "repostOf")	
	private List<Tweet> reposts;

	@ManyToMany
	@JoinTable
	private List<User> likes;

	@ManyToMany
	@JoinTable
	private List<Hashtag> hashtags;

	@ManyToMany
	@JoinTable
	private List<User> usersMentioned;

	@Override
	public int compareTo(Tweet t) {
		return this.getPosted().compareTo(t.getPosted());
	}
}




