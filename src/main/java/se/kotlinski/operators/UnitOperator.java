package se.kotlinski.operators;

import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.teams.Team;

public class UnitOperator {

  public void setBaseUnitTarget(final Team team, final Building enemyBaseBuilding) {
    for (Unit unit : team.units.values()) {
      unit.setBaseBuildingTarget(enemyBaseBuilding);
    }
  }

}
