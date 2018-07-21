package com.kdm

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class KDM extends ApplicationAdapter {
  private[kdm] var batch : SpriteBatch = null
  private[kdm] var img : Texture = null

  private var cultist_black : Texture = null
  private var cultist_white : Texture = null
  private var red_mark : Texture = null
  private var yellow_mark : Texture = null

  override def create(): Unit = {
    batch = new SpriteBatch
    img = new Texture("tile.png")
    cultist_black = new Texture("cultist_black.png")
    cultist_white = new Texture("cultist_white.png")
    red_mark = new Texture("red_mark.png")
    yellow_mark = new Texture("yellow_mark.png")
  }

  override def render(): Unit = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()

    for (line <- 0 to 16) {
      for (col <- 0 to 22) {
        batch.draw(img, col * 80, line * 80)
      }
    }

    batch.draw(cultist_black, 3 * 80, 4 * 80)
    batch.draw(yellow_mark, 3 * 80, 4 * 80)
    batch.draw(cultist_white, 3 * 80, 5 * 80)
    batch.draw(red_mark, 3 * 80, 5 * 80)

    batch.end()
  }

  override def dispose(): Unit = {
    batch.dispose()
    img.dispose()
  }
}