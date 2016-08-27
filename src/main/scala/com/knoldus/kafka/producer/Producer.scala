package com.knoldus.kafka.producer


import java.util.Properties
import java.util.concurrent.Future

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.{Logger, LoggerFactory}


case class Producer(servers: String) {

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
    /*convert into scala future
     val recordMetadataResponse = producer.send(message)
        val promise = Promise[RecordMetadata]()
        Future {
          promise.complete(Try(recordMetadataResponse.get()))
        }
        promise.future*/
    producer.send(message)
  }

  def close(): Unit = producer.close()
}

