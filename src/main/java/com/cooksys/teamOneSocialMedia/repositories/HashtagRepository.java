package com.cooksys.teamOneSocialMedia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.User;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	Optional<Hashtag> findById(Long id);

	Optional<User> findByLabel(String label);
}
