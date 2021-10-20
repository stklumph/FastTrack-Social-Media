package com.cooksys.teamOneSocialMedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	Optional<Hashtag> findById(Long id);

	Optional<Hashtag> findByLabel(String label);

	List<Hashtag> findByLabelIn(List<String> tags);

}
