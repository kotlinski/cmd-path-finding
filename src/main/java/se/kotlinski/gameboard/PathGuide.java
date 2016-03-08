package se.kotlinski.gameboard;

import se.kotlinski.models.Position;

public class PathGuide {

  public Position calculateNextPosition(Position fromPosition, Position toPosition) {
    int xDiff = fromPosition.x - toPosition.x;
    int yDiff = fromPosition.y - toPosition.y;

    int xDirection = 0;
    int yDirection = 0;

    if (Math.abs(xDiff) > Math.abs(yDiff)) {
      if (xDiff > 0) {
        xDirection = -1;
      }
      else if (xDiff < 0) {
        xDirection = 1;
      }
    }
    else {
      if (yDiff > 0) {
        yDirection = -1;
      }
      else if (yDiff < 0) {
        yDirection = 1;
      }
    }


    System.out.println("from position" + fromPosition);


    Position position = new Position(fromPosition.x + xDirection,
                                     fromPosition.y + yDirection,
                                     fromPosition.heightLevel);

    System.out.println("new position" + position);

    return position;
  }


}
