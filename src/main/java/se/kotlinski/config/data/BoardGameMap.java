package se.kotlinski.config.data;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.models.Position;

import java.util.Map;

public class BoardGameMap {
  private final MapAttributes mapAttributes;
  private final Map<Position, Unit> units;

  public BoardGameMap(MapAttributes mapAttributes, Map<Position, Unit> units) {
    this.mapAttributes = mapAttributes;
    this.units = units;
  }

  public final MapAttributes getMapAttributes() {
    return mapAttributes;
  }

  public final Map<Position, Unit> getUnits() {
    return units;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BoardGameMap that = (BoardGameMap) o;

    if (mapAttributes != null ? !mapAttributes.equals(that.mapAttributes) : that.mapAttributes != null) {
      return false;
    }
    if (units != null ? !units.equals(that.units) : that.units != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = mapAttributes != null ? mapAttributes.hashCode() : 0;
    result = 31 * result + (units != null ? units.hashCode() : 0);
    return result;
  }
}
