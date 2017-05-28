package com.knoldus.stream

import akka.actor.{Actor, ActorRef, Props}
import com.knoldus.kafka.producer.KafkaProducerActor._
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import org.slf4j.LoggerFactory


class StreamHandler(kafkaProducer: ActorRef, topic: String) extends Actor {

  import StreamHandler._

  val logger = LoggerFactory.getLogger(this.getClass())

  implicit val formats = DefaultFormats

  def receive: Receive = {
    case Tweet(feed) =>
      val json = write(feed)
      kafkaProducer ! TweetJson(json, topic)

    case invalidMessage =>
      logger.warn("No handler for this message " + invalidMessage)
  }

}


object StreamHandler {

  def props(kafkaProducer: ActorRef, topic: String) = Props(classOf[StreamHandler], kafkaProducer, topic)

  case class Tweet(feed: Map[String, String])

}