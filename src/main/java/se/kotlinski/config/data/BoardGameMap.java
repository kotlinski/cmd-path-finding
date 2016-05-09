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

  public MapAttributes getMapAttributes() {
    return mapAttributes;
  }

  public Map<Position, Unit> getUnits() {
    return units;
  }
}
