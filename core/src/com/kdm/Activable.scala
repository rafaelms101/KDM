package com.kdm

abstract class Activable(item: Item) {
  final def activate(): Unit = {
    item.owner.energy -= 1
    effect()
  }

  protected def effect(): Unit
}
