package com.kdm.items

import com.kdm.Constants._
import com.kdm.{Item, Player}

abstract class Armor(imgPath: String, val armorValue: Int, val bodyPart: BodyParts) extends Item(imgPath) {
  override def keywords: List[Keywords] = List(armor)

  override protected def addEffect(player: Player, line: Int, col: Int): Unit = {
    player.armor(bodyPart) = armorValue
  }

  override protected def removeEffect(player: Player, line: Int, col: Int): Unit = {
    player.armor(bodyPart) = 0
  }

  override protected def _canBeEquipped(player: Player, line: Int, col: Int): Boolean = {
    ! player.grid.hasAny {
      case armor: Armor => armor.bodyPart == bodyPart
      case _ => false
    }
  }
}