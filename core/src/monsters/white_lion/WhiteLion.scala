package monsters.white_lion

import com.kdm.Monster

class WhiteLion extends Monster("whiteLion.png") {
  override def startingTiles: List[(Int, Int)] = List((1, 10), (1, 11), (2, 12), (3, 13), (4, 14), (5, 15), (6, 16), (7, 17), (8, 17),
    (9, 16), (10, 15), (11, 14), (12, 13), (13, 12), (14, 11), (14, 10),
    (13, 9), (12, 8), (11, 7), (10, 6), (9, 5), (8, 4), (7, 4),
    (6, 5), (5, 6), (4, 7), (3, 8), (2, 9))
}
