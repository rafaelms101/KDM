package com.kdm
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.kdm.Constants._

import scala.collection.mutable

object Tile {
  var highlightTextures = mutable.Map[HighlightColor, Texture]()

  def loadHighlightTextures(): Unit = {
    if (highlightTextures.size == 0) {
      highlightTextures(red) = new Texture(Gdx.files.internal("red_mark.png"))
      highlightTextures(yellow) = new Texture(Gdx.files.internal("yellow_mark.png"))
    }

  }
}

class Tile(val line: Int, val col: Int) extends Node {
  var terrain: Terrain = null
  var creatureOn: Creature = null

  override def _mouseIsInside(mx: Int, my: Int): List[Node] = {
    val nodes = super._mouseIsInside(mx, my)
    if (x <= mx && mx <= x + 80 && y <= my && my <= y + 80) this :: nodes else nodes
  }

  private var highlight: TextureNode = null
  def highlight(color: HighlightColor): Unit = {
    highlight = new TextureNode(Tile.highlightTextures(color))
    addNode(highlight)
  }

  def removeHighlight(): Unit = {
    removeNode(highlight)
    highlight = null
  }
}
