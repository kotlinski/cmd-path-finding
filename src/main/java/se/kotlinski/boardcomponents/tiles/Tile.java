package se.kotlinski.boardcomponents.tiles;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

public abstract class Tile extends BoardComponentBase {

  private final TileType tileType;

  public Tile(final TileType tileType, final Position position) {
    super(position, Team.NEUTRAL);
    this.tileType = tileType;
  }

  public TileType getTileType() {
    return tileType;
  }

}
