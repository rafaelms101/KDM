package com.kdm

import com.badlogic.gdx.{Gdx, InputProcessor}

abstract class Scene extends Node with InputProcessor  {
  def setup(): Unit

  private var eventHandlerList = List(new EventHandler)

  def pushEventHandler(): Unit = {
    pushEventHandler(new EventHandler)
  }

  def pushEventHandler(eventProcessor: EventHandler): Unit = {
    eventHandlerList = eventProcessor :: eventHandlerList
  }

  def popEventHandler(): Unit = {
    eventHandlerList = eventHandlerList.tail
  }

  def registerShortcut(keycode: Int)(fn: => Unit): Unit = {
    eventHandlerList.head.shortcutMap(keycode) = fn _
  }

  override def keyDown(keycode: Int) : Boolean = {
    eventHandlerList.head.shortcutMap.get(keycode) match {
      case Some(fn) => fn()
      case None =>
    }

    true
  }

  def registerClickable(node: Node)(fn: => Unit): Unit = {
    eventHandlerList.head.clickable(node) = fn _
  }

  def removeClickable(node: Node): Unit = {
    eventHandlerList.head.clickable.remove(node)
  }

  def registerHoverable(node: Node)(fn: => Unit): Unit = {
    eventHandlerList.head.hoverable(node) = fn _
  }

  def removeHoverable(node: Node): Unit = {
    eventHandlerList.head.hoverable.remove(node)
  }

  def removeShortcut(keycode: Int): Unit = {
    eventHandlerList.head.shortcutMap.remove(keycode)
  }

  override def touchDown(screenX: Int, _screenY: Int, pointer: Int, button: Int) : Boolean  = {
    val screenY: Int = Gdx.graphics.getHeight - _screenY

    if (kdm.game.closeButton.mouseIsInside(screenX, screenY).nonEmpty) {
      Gdx.app.exit()
    }

    for ((node, fn) <- eventHandlerList.head.clickable) {
      if (node.mouseIsInside(screenX - node.origin_x, screenY - node.origin_y).nonEmpty) {
        fn()
      }
    }

    true
  }

  override def mouseMoved(screenX: Int, _screenY: Int) : Boolean  = {
    val screenY: Int = Gdx.graphics.getHeight - _screenY

    for ((node, fn) <- eventHandlerList.head.hoverable) {
      if (node.mouseIsInside(screenX - node.origin_x, screenY - node.origin_y).nonEmpty) {
        fn()
      }
    }

    true
  }

  override def keyUp(keycode: Int): Boolean = true

  override def keyTyped(character: Char): Boolean = true

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = true

  override def scrolled(amount: Int): Boolean = true
}
