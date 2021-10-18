package com.cooksys.teamOneSocialMedia;

import java.sql.Timestamp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.teamOneSocialMedia.entities.Hashtag;
import com.cooksys.teamOneSocialMedia.entities.Tweet;
import com.cooksys.teamOneSocialMedia.entities.User;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Profile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
	  User u1 = new User();
	  u1.setCredentials(new Credentials());
	  u1.setProfile(new Profile());
	  
	  Tweet t1 = new Tweet();
	  t1.setAuthor(u1);
	  t1.setContent("Howdy");
	  
	  Hashtag h1 = new Hashtag();
	  h1.setLabel("greeting");
	  //t1.setHashtags(new List<Hashtag>() {new Hashtag());
	  
  }
  
  
}