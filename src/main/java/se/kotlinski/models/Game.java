package se.kotlinski.models;

import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.buildings.BuildingType;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.GameBoard;

import java.util.List;

public class Game {

  public final GameBoard gameBoard;
  public List<Unit> units;
  public List<Building> buildings;
  public boolean isRunning = true;

  public Game(final List<Unit> units,
              final List<Building> buildings,
              final GameBoard gameBoard) {
    this.units = units;
    this.gameBoard = gameBoard;
    this.buildings = buildings;
  }

  public boolean isGameRunning() {
    for (Building building : buildings) {
      if (building.getBuildingType() == BuildingType.BASE) {
        for (Unit unit : units) {
          if (unit.getTeam() != building.getTeam()) {
            if (unit.getPosition().equals(building.getPosition())) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }
}
