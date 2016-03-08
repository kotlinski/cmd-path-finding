package se.kotlinski.gameboard.cmd;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.tiles.Tile;
import se.kotlinski.teams.Team;

public class ColorTable {

  public ColorTable() {
  }

  public String getColorForUnit(final BoardComponentBase unit) {
    Team team = unit.getTeam();
    switch (team) {
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
