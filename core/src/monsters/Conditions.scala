package monsters
import com.kdm.Constants._
import com.kdm.Player

object Conditions {
  object Closest extends TargetCondition {
    override def filter(players: List[Player]): List[Player] = {
      val monster = kdm.showdown.monster
      val minDist = players.map(monster.distance).min
      players.filter(player => monster.distance(player) == minDist)
    }
  }

  object Thread extends SingleCondition {
    override def singleFilter(player: Player): Boolean = {
      player.threat
    }
  }

  object Facing extends SingleCondition {
    override def singleFilter(player: Player): Boolean = {
      val monster = kdm.showdown.monster

      if (monster.facing == north) player.line > monster.line + 1
      else if (monster.facing == east) player.col > monster.col + 1
      else if (monster.facing == south) player.line < monster.line
      else player.col < monster.col
    }
  }

  object InRange extends SingleCondition {
    override def singleFilter(player: Player): Boolean = {
      kdm.showdown.monster.inRange(player)
    }
  }

  object InFieldOfView extends SingleCondition {
    override def singleFilter(player: Player): Boolean = {
      ! kdm.showdown.monster.blindSpots.exists {pos =>
        player.distance(pos) == 0
      }
    }
  }
}
