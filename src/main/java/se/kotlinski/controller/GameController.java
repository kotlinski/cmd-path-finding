package se.kotlinski.controller;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.cmd.GameBoardDrawer;
import se.kotlinski.gameboard.PathGuide;
import se.kotlinski.models.Game;
import se.kotlinski.models.Position;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class GameController {

  private final Game game;
  private final GameBoardDrawer gameBoardDrawer;
  private final PathGuide pathGuide;

  public GameController(final Game game) {
    this.game = game;
    gameBoardDrawer = new GameBoardDrawer(game);
    pathGuide = new PathGuide();
  }


  public boolean startGame() throws InterruptedException {
    endGameHandler();

    while (game.isGameRunning()) {
      gameBoardDrawer.drawGameboard();

      moveUnits();

      Thread.sleep(300);
    }
    return true;
  }

  private void moveUnits() {
    for (Unit unit : game.units) {
      Position position = pathGuide.calculateNextPosition(unit.getPosition(), unit.getTarget());
      unit.setPosition(position);
    }
  }


  private void endGameHandler() {

    Signal.handle(new Signal("INT"), new SignalHandler() {
      // Signal handler method
      public void handle(Signal signal) {
        System.out.println("Got signal" + signal);
        game.isRunning = false;
      }
    });
  }

}
