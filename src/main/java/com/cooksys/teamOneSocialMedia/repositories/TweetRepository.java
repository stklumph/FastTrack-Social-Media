package com.cooksys.teamOneSocialMedia.repositories;

import java.util.List;
import java.util.Optional;

import com.cooksys.teamOneSocialMedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {
	List<Tweet> findByDeletedFalseOrderByPostedDesc();
	
	Optional<Tweet> findByIdAndDeletedFalse(Integer id);

	List<Tweet> findByAuthorAndDeletedFalse(User author);
}
