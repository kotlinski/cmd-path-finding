package se.kotlinski.operators;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

import java.util.List;

public class UnitOperator {

  public void setBaseTarget(List<Unit> units, Team fromTeam, Position targetPosition) {
    for (Unit unit : units) {
      if (unit.getTeam().equals(fromTeam)) {
        unit.setTarget(targetPosition);
      }
    }
  }

}
