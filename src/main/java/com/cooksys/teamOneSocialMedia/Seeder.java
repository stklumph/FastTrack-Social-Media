package com.cooksys.teamOneSocialMedia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Profile;
import com.cooksys.teamOneSocialMedia.repositories.HashtagRepository;
import com.cooksys.teamOneSocialMedia.repositories.TweetRepository;
import com.cooksys.teamOneSocialMedia.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

	private final UserRepository userRepository;
	private final TweetRepository tweetRepository;
	private final HashtagRepository hashtagRepository;
	
  @Override
  public void run(String... args) throws Exception {
	  User u1 = new User();
	  Credentials c1 = new Credentials();
	  c1.setUsername("username1");
	  c1.setPassword("password1");
	  u1.setCredentials(c1);
	  
	  Profile p1 = new Profile();
	  p1.setEmail("email@email.com");
	  p1.setFirstName("You");
	  p1.setLastName("One");
	  p1.setPhone("111-222-3333");
	  u1.setProfile(p1);
	  u1.setJoined(Timestamp.valueOf("2007-09-23 10:10:10.0"));
	  
	  Tweet t1 = new Tweet();
	  t1.setAuthor(u1);
	  t1.setContent("Howdy");
	  
	  Hashtag h1 = new Hashtag();
	  h1.setLabel("greeting");
	  Hashtag h2 = new Hashtag();
	  h2.setLabel("hello");
	  
	  List<Hashtag> hashtagList = new ArrayList<>();
	  hashtagList.add(h1);
	  hashtagList.add(h2);
	  t1.setHashtags(hashtagList);
	  t1.setPosted(Timestamp.valueOf("2007-09-23 10:10:10.0"));
	  h1.setFirstUsed(Timestamp.valueOf("2007-09-23 10:10:10.0"));
	  h1.setLastUsed(Timestamp.valueOf("2007-09-23 10:10:10.0"));
	  h2.setFirstUsed(Timestamp.valueOf("2007-09-23 10:10:10.0"));
	  h2.setLastUsed(Timestamp.valueOf("2007-09-23 10:10:10.0"));
	  
	  
	  hashtagRepository.saveAndFlush(h1);
	  hashtagRepository.saveAndFlush(h2);
	  userRepository.saveAndFlush(u1);
	  tweetRepository.saveAndFlush(t1);
	  
	  
	  
  }
  
  
}