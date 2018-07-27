package com.kdm

import com.badlogic.gdx.scenes.scene2d.Actor

object Implicits {
  implicit def actorToNode(actor: Actor): Node = {
    new ActorNode(actor)
  }
}
