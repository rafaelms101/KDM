package com.kdm

class Creature(img: String) extends TextureNode(img) {
  var line: Int = 0
  var col: Int = 0

  def moveTo(tile: Tile): Unit = {
    tile.creatureOn = this
    x = tile.x
    y = tile.y
    line = tile.line
    col = tile.col
  }
}
