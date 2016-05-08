package se.kotlinski.teams;

import java.util.HashSet;
import java.util.Set;

import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.units.Unit;

public class Team {

  final public Building baseBuilding;
  final public Set<Unit> units;

  public Team(Building baseBuilding) {
    this.baseBuilding = baseBuilding;
    this.units = new HashSet<>();
  }

}
