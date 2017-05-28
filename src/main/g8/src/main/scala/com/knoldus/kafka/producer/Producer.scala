package com.knoldus.kafka.producer


import java.util.Properties

import akka.actor.ActorSystem
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContextExecutor, Future, Promise}
import scala.util.Try


case class Producer(servers: String, dispatcher: ExecutionContextExecutor) {


  val logger: Logger = LoggerFactory.getLogger(this.getClass())

  private val props: Properties = new Properties

  props.put("bootstrap.servers", servers)
  props.put("acks", "all")
  props.put("retries", "5")
  props.put("key.serializer", classOf[StringSerializer].getName)
  props.put("value.serializer", classOf[StringSerializer].getName)

  private val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](props)


  def send(topic: String, record: String): Future[RecordMetadata] = {
    val message: ProducerRecord[String, String] = new ProducerRecord[String, String](topic, record, record)
    logger.info("Sending message to kafka cluster .....")
    val recordMetadataResponse = producer.send(message)
    val promise = Promise[RecordMetadata]()
    Future {
      promise.complete(Try(recordMetadataResponse.get()))
    }(dispatcher)
    promise.future
  }

  def close(): Unit = producer.close()

}


