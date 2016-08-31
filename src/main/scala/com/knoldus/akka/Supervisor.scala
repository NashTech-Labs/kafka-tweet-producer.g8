package com.knoldus.akka

import akka.actor.SupervisorStrategy.Resume
import akka.actor.{Actor, OneForOneStrategy, Props, Terminated}
import org.slf4j.LoggerFactory

import scala.concurrent.duration.DurationInt


class Supervisor extends Actor {

  val logger = LoggerFactory.getLogger(this.getClass())

  val maxNrOfRetries = 10

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries, withinTimeRange = 1 second)({
    case ex: Exception =>
      logger.error("Actor Instance is dead and Recreating ", ex)
      Resume
  })


  def receive: PartialFunction[Any, Unit] = {

    case (props: Props, name: String) =>
      logger.info("creating child an actor...")
      val child = context.actorOf(props, name)
      context.watch(child)
      sender ! child

    case Terminated(child) =>
      logger.error(s"[$child] is dead")

    case invalidMessage =>
      logger.warn("No handler for this message " + invalidMessage)
  }

}


object Supervisor {

  def props() = Props(classOf[Supervisor])

}