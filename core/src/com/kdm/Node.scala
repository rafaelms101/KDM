package com.kdm

import com.badlogic.gdx.graphics.g2d.Batch

class Node {
  private var _children = List[Node]()
  private var _visible = true

  var parent: Node = null

  private var _x: Int = 0
  def x = _x
  def x_=(v: Int): Unit = {
    _x = v
  }

  private var _y: Int = 0
  def y = _y
  def y_=(v: Int): Unit = {
    _y = v
  }

  def origin_x: Int = if (parent == null) 0 else parent.origin_x + parent.x
  def origin_y: Int = if (parent == null) 0 else parent.origin_y + parent.y

  def children : List[Node] = _children

  def visible: Boolean = _visible

  def toogle(): Unit = {
    _visible = ! _visible
  }

  def show(): Unit = {
    _visible = true
  }

  def hide(): Unit = {
    _visible = false
  }

  private def path(list: List[Node], transform: Node => List[Node]): List[Node] = {
    list match {
      case head :: tail =>
        val ts = transform(head)
        if (ts.nonEmpty) head :: ts else path(tail, transform)
      case _ => Nil
    }
  }

  final def mouseIsInside(mx: Int, my: Int): List[Node] = {
    if (! visible) Nil else _mouseIsInside(mx, my)
  }

  protected def _mouseIsInside(mx: Int, my: Int): List[Node] = {
    path(children, _.mouseIsInside(mx - x, my - y))
  }

  def addNode(node: Node): Unit = {
    _children = _children :+ node
    node.parent = this
  }

  def addNode(node: Node, x: Int, y: Int): Unit = {
    addNode(node)
    node.x = x
    node.y = y
  }

  def removeNode(node: Node): Unit = {
    _children = _children.filterNot(_ == node)
  }

  def parents: List[Node] = {
    if (parent == null) Nil
    else parent :: parent.parents
  }

  def draw(batch: Batch, baseX: Int, baseY: Int): Unit = {
    _children.foreach { node =>
      if (node.visible) node.draw(batch, baseX + x, baseY + y)
    }
  }
}
