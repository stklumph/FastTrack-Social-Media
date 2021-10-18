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
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "author_id")
	@Column(nullable = false)
	private User author;

	@Column(nullable = false, updatable = false)
	private Timestamp posted;

	private boolean deleted;

	private String content;

	@OneToMany(mappedBy = "replies")
	private Tweet inReplyTo;

	@ManyToOne
	@JoinColumn(name = "in_reply_to_ID")
	private List<Tweet> replies;

	@OneToMany(mappedBy = "reposts")
	private Tweet repostOf;

	@ManyToOne
	@JoinColumn(name = "repost_of_ID")
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
}