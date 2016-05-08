package se.kotlinski.boardcomponents.units;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public abstract class Unit extends BoardComponentBase {

  private final UnitType unitType;
  public Position target;
  public Building enemyBaseBuilding;

  public Unit(UnitType unitType, Position position, TeamId teamId) {
    super(teamId, position);
    this.unitType = unitType;
  }

  public Position getTarget() {
    return target;
  }

  public Building getEnemyBaseBuilding() {
    return enemyBaseBuilding;
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
