package com.kdm.items

import com.kdm.Constants._
import com.kdm.{Constants, Item, Player}

class Weapon(imgPath: String, val speed: Int, val accuracy: Int, val strength: Int) extends Item(imgPath) {
  override def keywords: List[Constants.Keywords] = List(weapon)

  override protected def addEffect(player: Player, line: Int, col: Int): Unit = {}

  override protected def removeEffect(player: Player, line: Int, col: Int): Unit = {}

  override protected def _canBeEquipped(player: Player, line: Int, col: Int): Boolean = true
}
