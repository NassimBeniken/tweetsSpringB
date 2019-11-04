package org.demo.tweets.service;

import org.demo.tweets.model.Tweet;

import java.util.List;
import java.util.Optional;

public interface TweetService<T> {

    T insert(T t);

    T save(T t);

    Optional<T> findByid(String id);

    List<Tweet> findAll();
}
