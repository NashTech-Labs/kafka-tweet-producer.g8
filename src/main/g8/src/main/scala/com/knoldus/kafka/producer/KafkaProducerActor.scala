package com.knoldus.kafka.producer

import akka.actor.{Actor, Props}
import org.slf4j.{Logger, LoggerFactory}


class KafkaProducerActor(producer: Producer) extends Actor {

  import KafkaProducerActor._

  val logger: Logger = LoggerFactory.getLogger(this.getClass())

  def receive: Receive = {
    case TweetJson(tweet, topic) =>
      producer.send(topic, tweet)

    case invalidMessage =>
      logger.warn("No handler for this message " + invalidMessage)
  }

}


object KafkaProducerActor {

  def props(producer: Producer) = Props(classOf[KafkaProducerActor], producer)

  case class TweetJson(tweet: String, topic: String)

}