package com.kdm

import monsters.white_lion.WhiteLion


class KDM extends Game {
  override def setup(): Unit = {
    kdm.game = this

    Tile.loadHighlightTextures()
    DialogBox.laodFont()
    kdm.showdown = new Showdown(new WhiteLion, List(new Player("cultist_black.png"), new Player("cultist_white.png")
      , new Player("cultist_yellow.png"),new Player("cultist_red.png")))
    currentScene = kdm.showdown
  }
}