package se.kotlinski.controller;

import java.util.ArrayList;
import java.util.List;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.PathGuide;
import se.kotlinski.gameboard.cmd.GameBoardDrawer;
import se.kotlinski.graph.base.Node;
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
      updateTargets(game.teams);
      moveUnits(game.teams, game.gameBoard.tiles);
      winner = game.getWinner();
      Thread.sleep(300);
    }
    System.out.println("winning team: " + winner + "!!!");
    return true;
  }

  private void updateTargets(List<Team> teams) {
    for (Team team : teams) {
      for (Unit unit : team.units.values()) {
        List<Team> opponentTeams = getOpponentTeams(teams, team);
        List<Unit> opponentUnits = new ArrayList<Unit>();
        for (Team opponentTeam : opponentTeams) {
          opponentUnits.addAll(opponentTeam.units.values());
        }
        Position closestEnemyPosition = findClosestEnemy(unit, opponentUnits);
        if (closestEnemyPosition != null) {
          unit.setTarget(closestEnemyPosition);
        } else {
          unit.setTarget(unit.getEnemyBaseBuilding().getPosition());
        }
      }
    }
  }

  private List<Team> getOpponentTeams(final List<Team> teams, final Team team) {
    List<Team> opponentTeams = new ArrayList<Team>();
    opponentTeams.addAll(teams);
    opponentTeams.remove(team);
    return opponentTeams;
  }

  private Position findClosestEnemy(final Unit unit, final List<Unit> opponents) {
    // Todo: Implement A*

    int shortestDistance = Integer.MAX_VALUE;
    Position position = null;
    for (Unit opponent : opponents) {
      int distanceToPosition = calculateDistanceToPosition(unit.position, opponent.position);
      if (distanceToPosition < shortestDistance) {
        shortestDistance = distanceToPosition;
        position = opponent.position;
      }
    }
    return position;
  }

  private int calculateDistanceToPosition(final Position fromPosition, final Position toPosition) {
    int yDiff = Math.abs(fromPosition.y - toPosition.y);
    int xDiff = Math.abs(fromPosition.x - toPosition.x);
    return yDiff + xDiff;
  }

  private void moveUnits(List<Team> teams, final Node[][] tiles) {
    for (Team team : teams) {
      for (Unit unit : team.units.values()) {
        Position oldPosition = unit.getPosition();
        Position newPosition = pathGuide.calculateNextPosition(oldPosition, unit.getTarget(), teams, tiles);
        unit.setPosition(newPosition);
      //  team.units.put(newPosition, unit);
        team.units.remove(oldPosition);
        System.out.println("Units hash: " + team.units.keySet());
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
