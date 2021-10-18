package com.cooksys.teamOneSocialMedia.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String label;

	@Column(nullable = false)
	private Timestamp firstUsed;

	@Column(nullable = false)
	private Timestamp lastUsed;

	@ManyToMany(mappedBy = "hashtags")
	private List<Tweet> tweets;
}