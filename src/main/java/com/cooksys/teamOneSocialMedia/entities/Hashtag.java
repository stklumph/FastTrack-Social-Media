package com.cooksys.teamOneSocialMedia.entities;

import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
//import com.cooksys.teamOneSocialMedia.entities.Tweet;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;
	private String label;
	private Timestamp firstUsed;
	private Timestamp lastUsed;
	@ManyToMany(mappedBy = "hashtags")
	private List<Tweet> tweets;
}