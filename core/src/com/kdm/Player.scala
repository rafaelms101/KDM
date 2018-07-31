package com.kdm

import Constants._
import com.badlogic.gdx.math.MathUtils

import scala.collection.mutable

class Player(img: String) extends Creature(img) {
  def distance(_line: Int, _col: Int): Int = {
    Math.abs(line - _line) + Math.abs(col - _col)
  }

  def distance(pos: (Int, Int)): Int = {
    distance(pos._1, pos._2)
  }

  var survival: Int = 1
  val movement: Attribute = new Attribute
  val speed: Attribute = new Attribute
  val accuracy: Attribute = new Attribute
  val strength: Attribute = new Attribute
  val luck: Attribute = new Attribute
  val evasion: Attribute = new Attribute

  var damageLevel = Map(Head -> 0, Body -> 0, Waist -> 0, Arms -> 0,
                                           Legs -> 0, Brain -> 0)
  var armor = mutable.Map(Head -> 0, Body -> 0, Waist -> 0, Arms -> 0, Legs -> 0, Brain -> 0)

  var grid = new Grid
  var threat = false

  var energy = 1
  var movementEnergy = 1

  def newRound(): Unit = {
    energy = 1
    movementEnergy = 1
  }

  def knockDown(): Unit = {
    //TODO: implement this
  }

  private def moveSingleTile(dir: (Int, Int)): Unit = {


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

  private def moveStraightTo(dest: (Int, Int)): Unit = {
    var dir = (line - dest._1, col - dest._2)
    dir = (dir._1 / Math.abs(dir._1), dir._2 / Math.abs(dir._2))

    while ((line, col) != dest) {
      moveSingleTile(dir)
    }
  }

  //TODO: we assume that only a monster can knock someone back
  def knockBack(distance: Int, src: List[(Int, Int)]): Unit = {
    var directions = List((0,1), (1,0), (-1,0), (0,-1))
    val possibleDests = directions.map {dir =>
      (dir._1 * distance + line, dir._2 * distance + col)
    }.filter(Util.bound(_) != (line, col))
    val minDist = possibleDests.map(Util.distance(_, (line, col))).min
    val validDests = possibleDests.filter(Util.distance(_, (line, col)) == minDist)
    val dest = Util.bound(Dice.randomElement(validDests))

    moveStraightTo(dest)
  }

  def collision(): Unit = {
    knockDown()

    if (kdm.showdown.tile((line, col)).creatureOn != this) {
      val monster = kdm.showdown.tile((line, col)).creatureOn.asInstanceOf[Monster]

      knockBack(5, monster.individualPositions)
    }
  }

  def sufferDamage(bodyPart: BodyPart, v: Int): Unit = {
    var dmg = v

    //first try to damage armor
    var armorQty = armor(bodyPart)
    var partialDamage = Math.min(armorQty, dmg)
    armorQty -= partialDamage
    dmg -= partialDamage

    damageLevel(bodyPart) = damageLevel(bodyPart) + dmg

    if (damageLevel(bodyPart) >= 3) {
      damageLevel(bodyPart) = 2
      sufferTrauma(bodyPart)
    }
  }

  def sufferTrauma(bodyPart: BodyPart): Unit = {
    //TODO: implement traumas
  }
//
}
