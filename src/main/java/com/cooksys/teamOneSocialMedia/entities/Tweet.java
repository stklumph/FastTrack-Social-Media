package com.cooksys.teamOneSocialMedia.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.Nullable;

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
	private User author;

	private Timestamp posted;

	@Nullable
	private String content;

	@ManyToOne
	@Nullable
	@JoinColumn(name = "in_reply_to_ID")
	private Tweet inReplyTo;

	@ManyToOne
	@Nullable
	@JoinColumn(name = "repost_of_ID")
	private Tweet repostOf;
}