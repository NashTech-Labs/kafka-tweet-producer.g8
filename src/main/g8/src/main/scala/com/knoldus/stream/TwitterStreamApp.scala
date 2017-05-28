package com.knoldus.stream

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern._
import akka.util.Timeout
import com.knoldus.akka.Supervisor
import com.knoldus.kafka.producer.{KafkaProducerActor, Producer}
import org.slf4j.LoggerFactory
import twitter4j.{StatusListener, TwitterStream, TwitterStreamFactory}

import scala.concurrent.duration._
import scala.util.{Failure, Success}


object TwitterStreamApp extends App {

  val logger = LoggerFactory.getLogger(this.getClass())

  implicit val timeout = Timeout(10 seconds)

  val actorSystem = ActorSystem()

  implicit val dispatcher = actorSystem.dispatcher

  val supervisor = actorSystem.actorOf(Supervisor.props(), "supervisor")

  val kafkaProducer = Producer("localhost:9092", dispatcher)
  val topic = "tweet_queue"


  val kafkaProducerActor = (supervisor ? (KafkaProducerActor.props(kafkaProducer), "kafkaProducerActor")).mapTo[ActorRef]

  kafkaProducerActor.flatMap { producerActor =>
    (supervisor ? (StreamHandler.props(producerActor, topic), "streamHandler")).mapTo[ActorRef]
  }.onComplete {
    case Success(streamHandler) =>
      val twitterStream: TwitterStream = new TwitterStreamFactory().getInstance
      val listener: StatusListener = new TwitterStatusListener(streamHandler)
      twitterStream.addListener(listener)
      twitterStream.sample("en")
      twitterStream.filter("startbucks", "sbux", "startbuck",
        "coffestartbuck", "coffee", "StarbucksUK", "StarbucksCanada", "StarbucksMY",
        "StarbucksIndia", "StarbucksIE", "StarbucksAu", "StarbucksFrance", "StarbucksMex", "StarBucksTweet")

    case Failure(ex) =>
      logger.error("Error in actor initialization ", ex)
      System.exit(0)

  }


}
