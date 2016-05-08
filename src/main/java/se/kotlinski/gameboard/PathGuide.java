package se.kotlinski.gameboard;

import java.util.List;

import se.kotlinski.graph.base.Node;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

public class PathGuide {

  public Position calculateNextPosition(Position fromPosition, Position toPosition, final List<Team> teams,
      Node[][] tiles) {
    int xDiff = fromPosition.x - toPosition.x;
    int yDiff = fromPosition.y - toPosition.y;

    int xDirection = 0;
    int yDirection = 0;

    if (Math.abs(xDiff) > Math.abs(yDiff)) {
      if (xDiff > 0) {
        xDirection = -1;
      } else if (xDiff < 0) {
        xDirection = 1;
      }
    } else {
      if (yDiff > 0) {
        yDirection = -1;
      } else if (yDiff < 0) {
        yDirection = 1;
      }
    }

    Position position = new Position(fromPosition.x + xDirection, fromPosition.y + yDirection,
        fromPosition.heightLevel);

    System.out.println("from position" + fromPosition);
    System.out.println("to position" + position);

    if (isMovementBlocked(position, teams, tiles)) {
      return fromPosition;
    }

    return position;
  }

  private boolean isMovementBlocked(final Position newPosition, final List<Team> teams, final Node[][] tiles) {
    for (Team team : teams) {
      boolean containsPosition = team.units.containsKey(newPosition);
      if (containsPosition) {
        System.out.println("UNIT IN POSITION!!!!");
        throw new RuntimeException();
      }
    }
    Node tile = tiles[newPosition.y][newPosition.x];

    switch (tile.getTileType()) {
    case MUD:
      return false;
    case WATER:
      return false;
    default:
      return true;
    }
  }

}
