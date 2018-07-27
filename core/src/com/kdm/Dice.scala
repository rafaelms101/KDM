package com.kdm

import com.kdm.Constants._

object Dice {
  def d10(): Int = {
    scala.util.Random.nextInt(10) + 1
  }

  def randomBodyPart: BodyPart = {
    scala.util.Random.shuffle(List(Head, Arms, Legs, Body, Waist, Head)).head
  }

  def randomElement[A](list: List[A]): A = {
    scala.util.Random.shuffle(list).head
  }
}
