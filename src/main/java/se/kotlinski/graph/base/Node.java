package se.kotlinski.graph.base;

import se.kotlinski.teams.Team;

import java.util.HashMap;
import java.util.Map;

public class Node {

  public final Integer x;

  public final Integer y;

  public final Boolean blockingEdge;

  public final Map<Team, Boolean> visibleForTeam;

  public final Map<Team, Node> shortestEdgeToNextTarget;

  public Node(final Integer x, final Integer y) {
    this.x = x;
    this.y = y;
    blockingEdge = false;
    visibleForTeam = new HashMap<Team, Boolean>();
    shortestEdgeToNextTarget = new HashMap<Team, Node>();
  }

  public Node(final Integer x, final Integer y, final Boolean blockingEdge) {
    this.x = x;
    this.y = y;
    this.blockingEdge = blockingEdge;
    visibleForTeam = new HashMap<Team, Boolean>();
    shortestEdgeToNextTarget = new HashMap<Team, Node>();
  }
}
