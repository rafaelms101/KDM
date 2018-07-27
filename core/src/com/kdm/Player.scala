package com.kdm

import Constants._

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

  //TODO: we assume that only a monster can knock someone back
  def knockBack(distance: Int, srcLine: Int, srcCol: Int): Unit = {

  }

  def collision(): Unit = {
    knockDown()

    if (kdm.showdown.tile((line, col)).creatureOn != this) {

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
