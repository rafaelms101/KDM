package com.kdm

import com.kdm.Constants.Keywords

abstract class Item(imgPath: String) extends TextureNode(imgPath) {
  def activableList: List[Activable] = List()

  private var _owner: Player = null

  def owner: Player = _owner
  def keywords: List[Keywords]

  final def equip(player: Player, line: Int, col: Int): Unit = {
    player.grid.addItem(this, line, col)
    _owner = player
    addEffect(player, line, col)
  }

  final def unequip(player: Player): Unit = {
    val (line, col) = player.grid.findPos(this)
    removeEffect(player, line, col)
    player.grid.removeItem(line, col)
  }

  final def canBeEquipped(player: Player, line: Int, col: Int): Boolean = {
    player.grid.item(line, col) == null && _canBeEquipped(player, line, col)
  }

  protected def addEffect(player: Player, line: Int, col: Int): Unit
  protected def removeEffect(player: Player, line: Int, col: Int): Unit
  protected def _canBeEquipped(player: Player, line: Int, col: Int): Boolean
}
