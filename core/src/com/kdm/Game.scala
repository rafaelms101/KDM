package com.kdm

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{ApplicationAdapter, Gdx}

abstract class Game extends ApplicationAdapter {
  private var _currentScene: Scene = null
  private var batch: SpriteBatch = null
  private var _closeButton: TextureNode with CloseButton = null

  def closeButton = _closeButton


  def currentScene_=(sc: Scene)= {
    sc.addNode(closeButton, Gdx.graphics.getWidth - _closeButton.width, Gdx.graphics.getHeight - _closeButton.height)
    _currentScene = sc
    Gdx.input.setInputProcessor(sc)
    sc.setup()
  }

  def currentScene: Scene = _currentScene

  override def create(): Unit = {
    batch = new SpriteBatch()
    _closeButton = new CloseButton
    setup
  }

  def setup : Unit

  override def render() {
    batch.begin()
    _currentScene.draw(batch, 0, 0)
    batch.end()
  }

  override def dispose(): Unit = {
    batch.dispose()
  }
}
