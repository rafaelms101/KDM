package com.kdm

import scala.collection.mutable.Map

class EventHandler {
  val shortcutMap = Map[Int, () => Unit]()
  val clickable = Map[Node, () => Unit]()
  val hoverable = Map[Node, () => Unit]()
}
