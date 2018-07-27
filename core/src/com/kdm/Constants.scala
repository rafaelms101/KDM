package com.kdm

object Constants {
  sealed trait Damage
  object NoDamage extends Damage
  object LowDamage extends Damage
  object HeavyDamage extends Damage

  sealed trait BodyPart
  object Head extends BodyPart
  object Body extends BodyPart
  object Waist extends BodyPart
  object Arms extends BodyPart
  object Legs extends BodyPart
  object Brain extends BodyPart

  sealed trait Keywords
  object armor extends Keywords
  object weapon extends Keywords
  object melee extends Keywords
  object stone extends Keywords

  sealed trait HighlightColor
  object red extends HighlightColor
  object yellow extends HighlightColor

  sealed trait CardType
  object basic extends CardType
  object advanced extends CardType
  object legendary extends CardType
  object special extends CardType

  sealed trait Direction
  object north extends Direction
  object south extends Direction
  object east extends Direction
  object west extends Direction

  sealed trait Token
  object p1 extends Token
  object m1 extends Token
}
