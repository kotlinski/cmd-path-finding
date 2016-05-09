package se.kotlinski.models;

import se.kotlinski.boardcomponents.HeightLevel;

public class Position {

  public int x;
  public int y;
  public HeightLevel heightLevel;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
    this.heightLevel = HeightLevel.GROUND;
  }

  public Position(int x, int y, HeightLevel heightLevel) {
    this.x = x;
    this.y = y;
    this.heightLevel = heightLevel;
  }

  @Override
  public String toString() {
    return "Position{" +
           "x=" + x +
           ", y=" + y +
           ", heightLevel=" + heightLevel +
           '}';
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Position position = (Position) o;

    if (x != position.x) {
      return false;
    }
    return y == position.y;

  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
