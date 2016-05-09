package se.kotlinski.gameboard.cmd;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.deprecated.controller.graph.base.Node;

public class CharTable {


  public String getCharForUnit(final Unit unit) {

    switch (unit.getUnitType()) {
      case INFANTRY:
        return "i";
    }

    throw new RuntimeException();
  }

  public String getCharForBuilding(final BoardComponentBase building) {
    return "#";
  }

  public String getCharForTile(final Node tile) {
    switch (tile.getTileType()) {
      case MUD:
        return "░";
      case WATER:
        return "▒";
      case BLOCK:
        return "█";
    }
    return null;
  }

}
