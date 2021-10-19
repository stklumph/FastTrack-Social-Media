package com.cooksys.teamOneSocialMedia.repositories;

import com.cooksys.teamOneSocialMedia.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDeletedFalse();

}
