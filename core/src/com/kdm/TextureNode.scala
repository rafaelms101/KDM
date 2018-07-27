package com.kdm

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch

class TextureNode(texture: Texture) extends Node {
  def this(filename: String) {
    this(new Texture(Gdx.files.internal(filename)))
  }

  override def draw(batch: Batch, baseX: Int, baseY: Int): Unit = {
    batch.draw(texture, baseX + x, baseY + y)
    super.draw(batch, baseX, baseY)
  }

  override protected def _mouseIsInside(mx: Int, my: Int): List[Node] = {
    val nodes = super._mouseIsInside(mx, my)
    if (x <= mx && mx <= x + texture.getWidth && y <= my && my <= y + texture.getHeight) this :: nodes else nodes
  }

  def width: Int = texture.getWidth
  def height: Int = texture.getHeight
}
