package com.kdm

import scala.util.control.Breaks._

class Grid {
  var items = Array.ofDim[Item](3, 3)

  def addItem(item: Item, line: Int, col: Int): Unit = {
    items(line)(col) = item
  }

  def removeItem(line: Int, col: Int): Unit = {
    items(line)(col) = null
  }

  def item(line: Int, col: Int): Item = {
    items(line)(col)
  }

  def findPos(item: Item): (Int, Int) = {
    var line = 0
    var col = 0

    breakable {
      while (line < 3) {
        col = 0

        while (col < 3) {
          if (items(line)(col) == item) break
          col += 1
        }

        line += 1
      }
    }

    (line, col)
  }

  def hasAny(filter: Item => Boolean): Boolean = {
    var result: Boolean = false

    breakable {
      for (line <- items) {
        for (item <- line) {
          if (item != null && filter(item)) {
            result = true
            break
          }
        }
      }
    }

    result
  }
}
