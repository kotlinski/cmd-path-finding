package se.kotlinski.boardcomponents.units;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

public abstract class Unit extends BoardComponentBase {

  private final UnitType unitType;
  public Position target;

  public Unit(final UnitType unitType, final Team team, final Position position) {
    super(position, team);
    this.unitType = unitType;
  }

  public Position getTarget() {
    return target;
  }

  public void setTarget(final Position targetPosition) {
    target = targetPosition;
  }

  public UnitType getUnitType() {
    return unitType;
  }

}
