package monsters

import com.kdm.Constants._
import com.kdm.{Dice, Monster, Player}

import scala.util.Random
import scala.util.control.Breaks.{break, breakable}

object AiCard {
  def pickTarget(conditions: List[List[TargetCondition]]): Option[Player] = {
    var targets: List[Player] = Nil

    breakable {
      for (conditionList <- conditions) {
        var players = kdm.showdown.players

        for (condition <- conditionList) {
          players = condition.filter(players)
        }

        if (players.nonEmpty) {
          targets = players
          break
        }
      }
    }

    if (targets.isEmpty) None
    else Some(Dice.randomElement(targets))
  }

  def basicAttack(monster: Monster, speed: Int, accuracy: Int, damage: Int, player: Player): Unit = {
    var hits = 0

    for (_ <- 1 to speed) {
      val atk = Dice.d10() + monster.accuracy()
      val target = accuracy + player.evasion()

      if (atk >= target) hits += 1
    }

    for (_ <- 1 to hits) {
      val bodyPart = Dice.randomBodyPart
      player.sufferDamage(bodyPart, damage + monster.damage())
    }
  }

  def moveAndAttack(monster: Monster, target: Player): Unit = {
    monster.tryToReach(target.line, target.col)
    //TODO: finish the implementation

  }
}

class AICard(val cardType: CardType) {
  def drawEffect(): Unit
}
