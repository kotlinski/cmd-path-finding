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
  public boolean equals(Object o) {
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
    if (y != position.y) {
      return false;
    }
    if (heightLevel != position.heightLevel) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    result = 31 * result + (heightLevel != null ? heightLevel.hashCode() : 0);
    return result;
  }
}
