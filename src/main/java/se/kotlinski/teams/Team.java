package se.kotlinski.teams;

import java.util.HashMap;

import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.models.Position;

public class Team {

  final public Building baseBuilding;
  final public HashMap<Position, Unit> units;

  public Team(final Building baseBuilding, final HashMap<Position, Unit> units) {
    this.baseBuilding = baseBuilding;
    this.units = units;
  }

}
