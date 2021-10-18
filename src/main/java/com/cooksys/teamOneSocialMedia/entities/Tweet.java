package com.cooksys.teamOneSocialMedia.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "author_id")
	@Column(nullable = false)
	private User author;

	@Column(nullable = false)
	private Timestamp posted;

	private String content;

	@ManyToOne
	@JoinColumn(name = "in_reply_to_ID")
	private Tweet inReplyTo;

	@ManyToOne
	@JoinColumn(name = "repost_of_ID")
	private Tweet repostOf;
}