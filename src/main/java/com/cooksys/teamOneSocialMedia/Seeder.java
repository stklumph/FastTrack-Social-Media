package com.cooksys.teamOneSocialMedia;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
		p1.setPhone("111-111-1111");
		u1.setProfile(p1);
		u1.setJoined(Timestamp.valueOf(LocalDateTime.now()));

		Tweet t1 = new Tweet();
		t1.setAuthor(u1);
		t1.setContent("Entry 1");

		Hashtag h1 = new Hashtag();
		h1.setLabel("start-up");
		Hashtag h2 = new Hashtag();
		h2.setLabel("introduction");

		List<Hashtag> hashtagList = new ArrayList<>();
		hashtagList.add(h1);
		hashtagList.add(h2);
		t1.setHashtags(hashtagList);
		t1.setPosted(Timestamp.valueOf(LocalDateTime.now()));
		h1.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
		h1.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
		h2.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
		h2.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));

		hashtagRepository.saveAndFlush(h1);
		hashtagRepository.saveAndFlush(h2);
		u1 = userRepository.saveAndFlush(u1);
		tweetRepository.saveAndFlush(t1);
		// _Entry
		// 2___________________________________________________________________________________
		User u2 = new User();
		Credentials c2 = new Credentials();
		c2.setUsername("username2");
		c2.setPassword("password2");
		u2.setCredentials(c2);

		Profile p2 = new Profile();
		p2.setEmail("email2@email.com");
		p2.setFirstName("You");
		p2.setLastName("Two");
		p2.setPhone("222-222-2222");
		u2.setProfile(p2);
		u2.setJoined(Timestamp.valueOf(LocalDateTime.now()));

		Tweet t2 = new Tweet();
		t2.setAuthor(u2);
		t2.setContent("Entry 2");

		Hashtag h3 = new Hashtag();
		h3.setLabel("introduction2");
		Hashtag h4 = new Hashtag();
		h4.setLabel("new");

		List<Hashtag> hashtagList2 = new ArrayList<>();
		hashtagList2.add(h3);
		hashtagList2.add(h4);
		t2.setHashtags(hashtagList2);
		t2.setPosted(Timestamp.valueOf(LocalDateTime.now()));
		h3.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
		h3.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
		h4.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
		h4.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));

		hashtagRepository.saveAndFlush(h3);
		hashtagRepository.saveAndFlush(h4);
		u2 = userRepository.saveAndFlush(u2);
		tweetRepository.saveAndFlush(t2);
		// _Entry
		// 3___________________________________________________________________________________
		User u3 = new User();
		Credentials c3 = new Credentials();
		c3.setUsername("username3");
		c3.setPassword("password3");
		u3.setCredentials(c3);
		
		List<User> follows = new ArrayList<>();
		follows.add(u1);
		follows.add(u2);
		u3.setFollowing(follows);
		
		
		Profile p3 = new Profile();
		p3.setEmail("email3@email.com");
		p3.setFirstName("You");
		p3.setLastName("Three");
		p3.setPhone("333-333-3333");
		u3.setProfile(p3);
		u3.setJoined(Timestamp.valueOf(LocalDateTime.now()));

		Tweet t3 = new Tweet();
		t3.setAuthor(u3);
		t3.setContent("Entry 3");
		List<User> likes = new ArrayList<>();
		likes.add(u1);
		likes.add(u2);
		t3.setLikes(likes);
		List<User> mentions = new ArrayList<>();
		mentions.addAll(likes);
		mentions.add(u3);
		t3.setUsersMentioned(mentions);
		// List<Tweet> replies = new ArrayList<>();
		// replies.add(t1);
		// replies.add(t2);
		t3.setInReplyTo(t2);
		t3.setRepostOf(t2);

		Hashtag h5 = new Hashtag();
		h5.setLabel("triple");
		Hashtag h6 = new Hashtag();
		h6.setLabel("3");

		List<Hashtag> hashtagList3 = new ArrayList<>();
		hashtagList3.add(h5);
		hashtagList3.add(h6);
		t3.setHashtags(hashtagList3);
		t3.setPosted(Timestamp.valueOf(LocalDateTime.now()));
		h5.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
		h5.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));
		h6.setFirstUsed(Timestamp.valueOf(LocalDateTime.now()));
		h6.setLastUsed(Timestamp.valueOf(LocalDateTime.now()));

		
		hashtagRepository.saveAndFlush(h5);
		hashtagRepository.saveAndFlush(h6);
		userRepository.saveAndFlush(u3);
		tweetRepository.saveAndFlush(t3);
//		u1 = userRepository.findByCredentialsUsername("username1").get();
//		u2 = userRepository.findByCredentialsUsername("username2").get();
//		u3 = userRepository.findByCredentialsUsername("username3").get();
//		u1.userFollowing(u3);
//		u1.userFollowing(u2);
//		userRepository.saveAndFlush(u1);
	}

}