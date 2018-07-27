package com.kdm

import com.kdm.Constants._

class Attribute {
  private var tokens = 0
  private var value = 0

  def addToken(token: Token): Unit = {
    if (token == p1) {
      tokens += 1
    } else {
      tokens -= 1
    }
  }

  def changeValue(diff: Int): Unit = {
    value = value + diff
  }

  def apply(): Int = {
    value + tokens
  }

  def resetAttribute(): Unit = {
    tokens = 0
  }
}
