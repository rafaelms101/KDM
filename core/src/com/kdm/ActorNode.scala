package com.kdm

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import Implicits._

class ActorNode(actor: Actor) extends Node {
  override def x = actor.getX.toInt
  override def x_=(v: Int): Unit = {
    actor.setX(v)
  }

  override def y = actor.getY.toInt
  override def y_=(v: Int): Unit = {
    actor.setY(v)
  }

  override def visible: Boolean = actor.isVisible

  override def show(): Unit = {
    actor.setVisible(true)
  }

  override def toogle(): Unit = {
    actor.setVisible(! actor.isVisible)
  }

  override def hide(): Unit = {
    actor.setVisible(false)
  }

  override protected def _mouseIsInside(mx: Int, my: Int): List[Node] = {
    val act = actor.hit(mx - x, my - y, false)
    if (act == null) Nil else List(act)
  }

  override def draw(batch: Batch, baseX: Int, baseY: Int): Unit = {
    x = baseX + x
    y = baseY + y
    actor.draw(batch, 1)
    x = x - baseX
    y = y - baseY
  }
}


