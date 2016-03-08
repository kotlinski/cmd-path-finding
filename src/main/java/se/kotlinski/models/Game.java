package se.kotlinski.models;

import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.GameBoard;
import se.kotlinski.teams.Team;

import java.util.List;

public class Game {

  public final GameBoard gameBoard;
  public List<Team> teams;
  public List<Building> buildings;
  public boolean isRunning = true;

  public Game(final List<Team> teams,
              final List<Building> buildings,
              final GameBoard gameBoard) {
    this.teams = teams;
    this.gameBoard = gameBoard;
    this.buildings = buildings;
  }

  public Team getWinner() {
    for (Team team : teams) {
      for (Unit unit : team.units.values()) {
        if (unit.position.equals(unit.getTarget())) {
          return team;
        }
      }
    }
    return null;
  }
}
