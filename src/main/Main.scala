package main

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.scene.Group
import javafx.scene.SceneAntialiasing
import javafx.scene.layout.VBox
import javafx.geometry.Pos
import javafx.application.Platform

object Main {
  def main(args: Array[String]) {
    Application.launch(classOf[Main], args: _*)
  }
}

class Main extends Application {

  private final val APP_WIDTH: Double = 800
  private final val APP_HEIGHT: Double = 600

  private final val mainMenuRoot = new Group()
  private final val levelEditorRoot = new Group()
  private final val gameRoot = new Group()

  private final val mainMenuScene = new Scene(
    mainMenuRoot,
    APP_WIDTH, APP_HEIGHT,
    false, SceneAntialiasing.BALANCED)

  private final val levelEditorScene = new Scene(
    levelEditorRoot,
    APP_WIDTH, APP_HEIGHT,
    false, SceneAntialiasing.BALANCED)

  private final val gameScene = new Scene(
    gameRoot,
    APP_WIDTH, APP_HEIGHT,
    false, SceneAntialiasing.BALANCED)

  private var stage: Stage = _

  override def start(primaryStage: Stage) {
    stage = primaryStage
    stage.setWidth(APP_WIDTH)
    stage.setHeight(APP_HEIGHT)
    stage.setResizable(false)
    stage.show

    buildMainMenu

    setScene(mainMenuScene)

  }

  def createButton(s: String, onAction: () => Unit, width: Double = 300, height: Double = 50): Button = {
    val button = new Button(s)
    button.setOnAction((e: ActionEvent) => {
      onAction()
    })
    button.setPrefSize(width, height)
    button
  }

  def buildMainMenu = {
    val children = mainMenuRoot.getChildren
    children.clear

    val playButton = createButton("Play", playClicked)
    val levelEditorButton = createButton("Level Editor", levelEditorClicked)
    val exitButton = createButton("Exit", Platform.exit)

    val vBox = new VBox()
    vBox.setPrefSize(APP_WIDTH, APP_HEIGHT)
    vBox.setAlignment(Pos.CENTER)
    vBox.setSpacing(10)
    vBox.getChildren.addAll(playButton, levelEditorButton, exitButton)

    children.addAll(vBox)
  }

  def playClicked() = {
    setScene(gameScene)
  }

  def levelEditorClicked() = {
    setScene(levelEditorScene)
  }

  def setScene(s: Scene): Unit = s match {
    case `mainMenuScene` => {
      stage.setTitle("Main Menu")
      stage.setScene(s)
    }
    case `levelEditorScene` => {
      stage.setTitle("Level Editor")
      stage.setScene(s)
    }
    case `gameScene` => {
      stage.setTitle("Game")
      stage.setScene(s)
    }
  }

}