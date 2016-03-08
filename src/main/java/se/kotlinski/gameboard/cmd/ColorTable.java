package se.kotlinski.gameboard.cmd;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.tiles.Tile;
import se.kotlinski.teams.TeamType;

public class ColorTable {

  public ColorTable() {
  }

  public String getTeamColor(final BoardComponentBase unit) {
    TeamType teamType = unit.getTeamType();
    switch (teamType) {
    case TEAM_A:
      return AnsiColor.ANSI_RED;
    case TEAM_B:
      return AnsiColor.ANSI_YELLOW;
    }
    throw new RuntimeException();
  }

  public String getColorForTile(final Tile tile) {
    switch (tile.getTileType()) {
    case MUD:
      return AnsiColor.ANSI_YELLOW;
    case WATER:
      return AnsiColor.ANSI_CYAN;
    case BLOCK:
      return AnsiColor.ANSI_PURPLE;
    }
    throw new RuntimeException();
  }

}
