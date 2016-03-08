package se.kotlinski.boardcomponents.units;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamType;

public abstract class Unit extends BoardComponentBase {

  private final UnitType unitType;
  public Position target;
  public Building enemyBaseBuilding;

  public Unit(final UnitType unitType, final TeamType teamType, final Position position) {
    super(position, teamType);
    this.unitType = unitType;
  }

  public Position getTarget() {
    return target;
  }

  public void setTarget(final Position targetPosition) {
    target = targetPosition;
  }

  public void setBaseBuildingTarget(final Building enemyBaseBuilding) {
    this.enemyBaseBuilding = enemyBaseBuilding;
    setTarget(enemyBaseBuilding.getPosition());
  }

  public UnitType getUnitType() {
    return unitType;
  }

}
