package com.github.weaselworks.storm.route;

import org.apache.camel.component.twitter.TwitterComponent;
import twitter4j.Status;

import java.net.URLEncoder;

/**
 * Created by steve on 19/10/2014.
 */
public class RouteBuilder extends org.apache.camel.builder.RouteBuilder{
    @Override
    public void configure() throws Exception {

        configureTwitterComponent();

        from("twitter://streaming/sample?type=event")
                .to("log:com.github.weaselworks.storm.route?level=DEBUG");

    }

    public void configureTwitterComponent(){

            TwitterComponent tc = getContext().getComponent("twitter", TwitterComponent.class);
            tc.setConsumerKey(System.getProperty("twitter4j.oauth.consumerKey"));
            tc.setConsumerSecret(System.getProperty("twitter4j.oauth.consumerSecret"));
            tc.setAccessToken(System.getProperty("twitter4j.oauth.accessToken"));
            tc.setAccessTokenSecret(System.getProperty("twitter4j.oauth.accessTokenSecret"));
    }
}
