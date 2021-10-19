package com.cooksys.teamOneSocialMedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
	List<Tweet> findByDeletedFalse();
}
