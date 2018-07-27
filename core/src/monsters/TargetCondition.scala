package monsters

import com.kdm.Player

abstract class TargetCondition {
  def filter(players: List[Player]): List[Player]
}

abstract class SingleCondition extends TargetCondition {
  def singleFilter(player: Player): Boolean

  override def filter(players: List[Player]): List[Player] = {
    players.filter(singleFilter)
  }
}
