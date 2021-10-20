package com.cooksys.teamOneSocialMedia.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.teamOneSocialMedia.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByIdAndDeletedFalse(Long id);

	List<User> findByDeletedFalse();

	Optional<User> findByCredentialsUsernameAndDeletedFalse(String string);

	Optional<User> findByCredentialsUsername(String string);

	List<User> findByDeletedFalseAndCredentialsUsernameIn(List<String> userList);
}
