package com.cooksys.teamOneSocialMedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> { // FIXME: update the id type if a Long is not
																			// used for the ID of the hashtags

}
