package com.kdm

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.utils.Align
import com.kdm.Implicits._

object DialogBox {
  var labelStyle: LabelStyle = _

  def laodFont(): Unit = {
    labelStyle = new LabelStyle()
    val fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("BebasNeue-Regular.ttf"));
    val fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter
    fontParameters.size = 48
    fontParameters.color = Color.WHITE
    fontParameters.borderWidth = 2
    fontParameters.borderColor = Color.BLACK
    fontParameters.borderStraight = true
    fontParameters.minFilter = TextureFilter.Linear
    fontParameters.magFilter = TextureFilter.Linear

    val customFont = fontGenerator.generateFont(fontParameters)
    labelStyle.font = customFont
  }
}

class DialogBox extends TextureNode("dialogBox.png") {
  private val dialogLabel: Label = new Label("", DialogBox.labelStyle)
  private val padding = 23

  addNode(dialogLabel)
  dialogLabel.setWrap(true)
  dialogLabel.setAlignment(Align.center)
  dialogLabel.setPosition(padding, padding)
  dialogLabel.setWidth(width - 2 * padding)
  dialogLabel.setHeight(height - 2 * padding)

  def text: String = dialogLabel.getText.toString

  def text_=(v: String){
    dialogLabel.setText(v)
  }
}
