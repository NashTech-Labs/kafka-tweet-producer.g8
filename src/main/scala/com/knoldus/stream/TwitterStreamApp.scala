package com.knoldus.stream

import twitter4j.{StatusListener, TwitterStream, TwitterStreamFactory}


object TwitterStreamApp extends App {

  val twitterStream: TwitterStream = new TwitterStreamFactory().getInstance

  val listener: StatusListener = new TwitterStatusListener()

  twitterStream.addListener(listener)
  twitterStream.sample("en")
  //twitterStream.filter("java","scala","akka","spark")

}
