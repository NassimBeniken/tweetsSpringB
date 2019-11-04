package org.demo.tweets.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.demo.tweets.dto.TweetDto;
import org.demo.tweets.model.Tweet;
import org.demo.tweets.service.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/tweets")
@Slf4j
@CrossOrigin(origins = "*")
public class TweetEndpoints {

    private final TweetServiceImpl tweetService;

    @Autowired
    public TweetEndpoints(TweetServiceImpl tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping(value = "/")
    public String home() {
        return "Tweets API";
    }

    @GetMapping("/all")
    public List<TweetDto> getAll() {
        List<Tweet> tweets = this.tweetService.findAll();
        List<TweetDto> tweetDtos = tweets.stream().map(Tweet::toDto).collect(Collectors.toList());
        return  tweetDtos;
    }

    @GetMapping(value = "/{id}")
    public Optional<TweetDto> findById(@PathVariable String id) {
        Optional<Tweet> tweet = tweetService.findByid(id);
        Optional<TweetDto> tweetDto = tweet.stream().map(Tweet::toDto).findAny();
        return tweetDto;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TweetDto> createTweet(@Valid @RequestBody TweetDto tweetDto, UriComponentsBuilder uriComponentsBuilder) {
        log.debug(tweetDto.toString());
        tweetDto.setId(UUID.randomUUID().toString());
        tweetService.save(tweetDto.toEntity());
        UriComponents uriComponent = uriComponentsBuilder.path("/api/v1/tweets/{id}").buildAndExpand(tweetDto.getId());
        return  ResponseEntity.status(HttpStatus.CREATED).location(uriComponent.toUri()).body(tweetDto);
    }
}
