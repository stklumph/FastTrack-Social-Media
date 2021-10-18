package com.cooksys.teamOneSocialMedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.teamOneSocialMedia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
