package org.demo.tweets.service;

import org.demo.tweets.model.Tweet;
import org.demo.tweets.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService<Tweet> {

    private TweetRepository tweetRepository;

    @Autowired
    public void setTweetRepository(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet insert(Tweet tweet) {
        return tweetRepository.insert(tweet);
    }

    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public Optional<Tweet> findByid(String id) {
        return tweetRepository.findById(id);
    }

    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }
}
