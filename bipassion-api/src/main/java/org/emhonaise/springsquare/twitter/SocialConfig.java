package org.emhonaise.springsquare.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class SocialConfig {

    @Value("${consumerKey}")
    private String consumerKey;

    @Value("${consumerSecret}")
        private String consumerSecret;

    @Value("${accessToken}")
        private String accessToken;

    @Value("${accessTokenSecret}")
           private String accessTokenSecret;

    @Bean
    public Twitter twitter() {
        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

}