package org.demo.tweets.repository;

import org.demo.tweets.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TweetRepository extends MongoRepository<Tweet, String> {

}
