package se.kotlinski.boardcomponents.units;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public abstract class Unit extends BoardComponentBase {

  private final UnitType unitType;
  public Position target;
  public Building enemyBaseBuilding;

  public Unit(UnitType unitType, TeamId teamId) {
    super(teamId);
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
    //setTarget(enemyBaseBuilding.getPosition());
    //Ask something where opponents buildings are

  }

  public UnitType getUnitType() {
    return unitType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Unit unit = (Unit) o;

    if (unitType != unit.unitType) {
      return false;
    }
    if (target != null ? !target.equals(unit.target) : unit.target != null) {
      return false;
    }
    if (enemyBaseBuilding != null ? !enemyBaseBuilding.equals(unit.enemyBaseBuilding) : unit.enemyBaseBuilding != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = unitType != null ? unitType.hashCode() : 0;
    result = 31 * result + (target != null ? target.hashCode() : 0);
    result = 31 * result + (enemyBaseBuilding != null ? enemyBaseBuilding.hashCode() : 0);
    return result;
  }
}
