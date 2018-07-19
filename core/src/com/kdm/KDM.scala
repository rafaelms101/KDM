package com.kdm

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class KDM extends ApplicationAdapter {
  private[kdm] var batch : SpriteBatch = null
  private[kdm] var img : Texture = null

  override def create(): Unit = {
    batch = new SpriteBatch
    img = new Texture("badlogic.jpg")
  }

  override def render(): Unit = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    batch.draw(img, 0, 0)
    batch.end()
  }

  override def dispose(): Unit = {
    batch.dispose()
    img.dispose()
  }
}