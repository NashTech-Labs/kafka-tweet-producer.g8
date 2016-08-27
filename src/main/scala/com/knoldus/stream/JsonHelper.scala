package com.knoldus.stream

import org.json4s.DefaultFormats
import org.json4s.native.Serialization

trait JsonHelper {

  implicit val formats = DefaultFormats

  def write[T <: AnyRef](value: T): String = Serialization.write(value)

}
