package se.kotlinski.controller;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.cmd.GameBoardDrawer;
import se.kotlinski.gameboard.PathGuide;
import se.kotlinski.models.Game;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;
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

    Team winner = null;
    while (winner == null) {
      gameBoardDrawer.drawGameboard();

      moveUnits();
      winner = game.getWinner();
      Thread.sleep(300);
    }
    System.out.println("winning team: " + winner + "!!!");
    return true;
  }

  private void moveUnits() {
    for (Team team : game.teams) {
      for (Unit unit : team.units.values()) {
        Position position = pathGuide.calculateNextPosition(unit.getPosition(), unit.getTarget());
        unit.setPosition(position);
      }
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
