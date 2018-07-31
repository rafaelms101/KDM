package com.kdm

import com.kdm.Constants._
import monsters.{AICard, HitLocationCard}
import util.control.Breaks._

abstract class Monster(img: String) extends Creature(img) {
  var facing: Direction = north

  def facingNorthBlindSpots: List[(Int, Int)]
  def blindSpots: List[(Int, Int)] = {
    facingNorthBlindSpots.map {pair =>
      if (facing == north) pair
      else if (facing == east) (- pair._2, pair._1)
      else if (facing == south) (-pair._1, -pair._2)
      else (pair._2, -pair._1)
    }
  }

  def distance(dline: Int, dcol: Int): Int = {
    individualPositions.map {src =>
      Util.distance(src, (dline, dcol))
    }.min
  }

  def distance(player: Player): Int = {
    distance(player.line, player.col)
  }

  def inRange(player: Player): Boolean = {
      distance(player) - 1 <= movement()
  }

  def startingTiles: List[(Int, Int)]

  val movement: Attribute = new Attribute
  val speed: Attribute = new Attribute
  var toughness: Attribute = new Attribute


  val accuracy: Attribute = new Attribute
  val damage: Attribute = new Attribute
  val evasion: Attribute = new Attribute
  val luck: Attribute = new Attribute
  var level: Int = -1

  def hitLocationCards: List[HitLocationCard]
  def allAICards: List[AICard]
  def basicAction: AICard

  private var deck: List[AICard] = Nil

  def setLevel(lvl: Int): Unit = {
    level = lvl
    prepareAIDeck(level)
  }

  def cardsByLevel: Map[Int, (Int, Int, Int, List[AICard])]

  private var activeCards: List[AICard] = Nil

  private def prepareAIDeck(level: Int): Unit = {
    var (nbasic, nadvanced, nlegendary, specials) = cardsByLevel(level)

    val cards = scala.util.Random.shuffle(allAICards)
    deck = Nil

    for (card <- cards) {
      if (card.cardType == basic && nbasic >= 1) {
        deck = card :: deck
        nbasic -= 1
      } else if (card.cardType == advanced && nadvanced >= 1) {
        deck = card :: deck
        nadvanced -= 1
      } else if (card.cardType == legendary && nlegendary >= 1) {
        deck = card :: deck
        nlegendary -= 1
      }
    }

    for (card <- specials) {
      activeCards = card :: activeCards
    }
  }

  def individualPositions: List[(Int, Int)] = List((line,col), (line+1,col), (line, col+1), (line+1,col+1))

  private var scheduledCollision: List[Player] = Nil

//TODO: what happens if a monster collides with a player and there is a impassable terrain right ahead
  def moveSingleTile(dir: (Int, Int)): Unit = {
    val oldPositions = individualPositions

    line = line + dir._1
    col = col + dir._2
    x = x + dir._1 * 80
    y = y + dir._2 * 80

    //collision verification
    individualPositions.foreach {pos =>
      val tile = kdm.showdown.tile(pos)

      if (tile.creatureOn != null) {
        tile.creatureOn match {
          case player: Player => scheduledCollision = player :: scheduledCollision
          case _ =>
        }
      }
    }

    oldPositions.foreach {pos =>
      kdm.showdown.tile(pos).creatureOn = null
    }

    individualPositions.foreach {pos =>
      kdm.showdown.tile(pos).creatureOn = this
    }
  }

  def directions(dline: Int, dcol: Int): List[Direction] = {
    var dirs: List[Direction] = Nil

    if (dline > line) dirs = north :: dirs
    if (dcol > col) dirs = east :: dirs
    if (dline < line) dirs = south :: dirs
    if (dcol < col) dirs = west :: dirs

    dirs
  }

  private def turnToFace(dline: Int, dcol: Int): Unit = {
    val dirs = directions(dline, dcol)
    //TODO: update graphically
    facing = Dice.randomElement(dirs)
  }

  def tryToReach(dy: Int, dx: Int): Unit = {
    scheduledCollision = Nil
    var steps = 0
    var dist = distance(dy, dx)

    while (steps <= movement() && dist >= 2) {
      val directions = scala.util.Random.shuffle(List((-1,0), (1,0), (0,-1), (0,1)))

      breakable {
        for (direction <- directions) {
          val newdist = distance(individualPositions.map(pos => (pos._1 + direction._1, pos._2 + direction._2)), dy, dx)
          if (newdist < dist) {
            dist = newdist
            moveSingleTile(direction)
            break
          }
        }
      }

      steps += 1
    }

    scheduledCollision.foreach(_.collision())
    turnToFace(dy, dx)
  }
}
