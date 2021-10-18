package com.cooksys.teamOneSocialMedia.entities;

import javax.persistence.*;

import com.sun.istack.Nullable;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    private Timestamp posted;
    @Nullable
    private String content;
    @ManyToOne
    @Nullable
    @JoinColumn(name = "in_reply_to_ID")
    private Tweet inReplyTo;
    @ManyToOne
    @Nullable
    @JoinColumn(name = "repost_of_ID")
    private Tweet repostOf;
}