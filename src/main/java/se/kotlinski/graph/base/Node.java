package se.kotlinski.graph.base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.kotlinski.boardcomponents.tiles.TileType;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.cmd.CharTable;
import se.kotlinski.teams.Team;

public class Node {

  public Integer x;
  public Integer y;

  public Boolean blockingEdge;

  public Map<Team, Boolean> visibleForTeam;

  public Map<Team, Node> shortestEdgeToNextTarget;
  public TileType tileType;
  public CharTable charTable;
  public Unit unit;
  public Set<Unit> units;

  public Node(Integer y, Integer x) {
    this.x = x;
    this.y = y;
    blockingEdge = false;
    visibleForTeam = new HashMap<>();
    shortestEdgeToNextTarget = new HashMap<>();
    units = new HashSet<>();
    tileType = TileType.MUD;

    charTable = new CharTable();
  }

  public Node(Integer x, Integer y, Boolean blockingEdge, TileType tileType) {
    this.x = x;
    this.y = y;
    this.blockingEdge = blockingEdge;
    this.tileType = tileType;
    visibleForTeam = new HashMap<>();
    shortestEdgeToNextTarget = new HashMap<>();

    charTable = new CharTable();
  }

  public TileType getTileType() {
    return tileType;
  }

  @Override
  public String toString() {
    return charTable.getCharForTile(this);
  }
}
