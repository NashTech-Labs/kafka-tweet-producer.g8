package com.knoldus.stream

import twitter4j.conf.ConfigurationBuilder
import twitter4j.{StatusListener, TwitterStream, TwitterStreamFactory}


object TwitterStreamApp extends App {

  val cb = new ConfigurationBuilder()
    .setOAuthConsumerKey("********")
    .setOAuthConsumerSecret("****************")
    .setOAuthAccessToken("*************")
    .setOAuthAccessTokenSecret("******************")

  val twitterStream: TwitterStream = new TwitterStreamFactory(cb.build()).getInstance

  val listener: StatusListener = new TwitterStatusListener()

  twitterStream.addListener(listener)
  twitterStream.sample("en")
  //twitterStream.filter("java","scala","akka","spark")

}
