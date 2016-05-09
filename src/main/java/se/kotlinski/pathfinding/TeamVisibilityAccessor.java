package se.kotlinski.pathfinding;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TeamVisibilityAccessor {

  public Set<Position> calculateVisiblePositionsForTeam(Map<Position, Unit> units, TeamId teamId){
    HashSet<Position> positions = new HashSet<>();

    for (Position position : units.keySet()) {
      positions.add(position);
      int visibilityLevel = 2;
      positions.addAll(addPositions(positions, visibilityLevel));
    }

    return positions;
  }

  private Set<Position> addPositions(Set<Position> positions, int level) {
    if (level == 0) {
      return positions;
    }
    Set<Position> positionsToAdd = new HashSet();

    Iterator<Position> positionIterator = positions.iterator();
    while (positionIterator.hasNext()) {
      Position next = positionIterator.next();
      positionsToAdd.add(new Position(next.x + 1, next.y));
      positionsToAdd.add(new Position(next.x - 1, next.y));
      positionsToAdd.add(new Position(next.x, next.y - 1));
      positionsToAdd.add(new Position(next.x, next.y + 1));
    }

    positions.addAll(positionsToAdd);

    return addPositions(positionsToAdd, level-1);
  }

}
