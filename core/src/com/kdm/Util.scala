package com.kdm

object Util {


  def bound(x: Int, min: Int, max: Int): Int = {
    Math.max(Math.min(x, max), min)
  }

  def bound(pos: (Int, Int), boundLine: (Int, Int), boundCol: (Int, Int)): (Int, Int) = {
    (Math.max(Math.min(pos._2, boundCol._2), boundCol._1), Math.max(Math.min(pos._2, boundLine._2), boundLine._1))
  }

  def bound(pos: (Int, Int)): (Int, Int) = {
    bound(pos, (0, 15), (0,21))
  }

  def distance(src: (Int, Int), dest: (Int, Int)): Int = {
    Math.abs(src._1 - dest._1) + Math.abs(src._2 - dest._2)
  }
}
