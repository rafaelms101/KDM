package com.kdm

import com.kdm.Constants._

class Showdown(val monster: Monster, val players: List[Player]) extends Scene {
  var board = Array.ofDim[Tile](16, 22)
  var visualBoard: TextureNode = _
  var dialogBox: DialogBox = _

  def addMonster(monster: Monster): Unit = {
    visualBoard.addNode(monster)
    monster.x = 10 * 80
    monster.y = 7 * 80
  }

  override def setup(): Unit = {
    dialogBox = new DialogBox
    addNode(dialogBox, 840, 0)
    dialogBox.text = "Insert the 4 playable characters"
    visualBoard = new TextureNode("board.png")
    addNode(visualBoard, 840, 160)
    addMonster(monster)

    for (line <- 0 until 16) {
      for (col <- 0 until 22) {
        val tile = new Tile(line, col)
        board(line)(col) = tile
        visualBoard.addNode(tile, col * 80, line * 80 )
      }
    }

    playersSetup()
  }

  def tile(pos: (Int, Int)): Tile = {
    tile(pos._1, pos._2)
  }

  def tile(line: Int, col: Int): Tile = {
    board(line)(col)
  }

  def beginShowdown(): Unit = {
    dialogBox.text = "The showdown begins"
  }

  def playersSetup(): Unit = {
    var notAdded = players
    pushEventHandler()

    for ((line, col) <- monster.startingTiles) {
      val tile = board(line)(col)
      tile.highlight(red)

      registerClickable(tile) {
        val player = notAdded.head
        player.moveTo(tile)
        visualBoard.addNode(player)
        removeClickable(tile)
        tile.removeHighlight()

        notAdded = notAdded.tail

        if (notAdded.isEmpty) {
          for ((line, col) <- monster.startingTiles) {
            board(line)(col).removeHighlight()
          }

          popEventHandler()
          beginShowdown()
        }
      }
    }
  }
}
