package se.kotlinski.boardcomponents.tiles;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public abstract class Tile extends BoardComponentBase {

  private final TileType tileType;

  public Tile(final TileType tileType, final Position position) {
    super(position, TeamId.NEUTRAL);
    this.tileType = tileType;
  }

  public TileType getTileType() {
    return tileType;
  }

}
