
package se.kotlinski.graph.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import se.kotlinski.graph.GraphPathImpl;
import se.kotlinski.graph.base.Graph;
import se.kotlinski.graph.base.Node;
import se.kotlinski.graph.traverse.ClosestFirstIterator;
import se.kotlinski.teams.Team;

public final class KotlinskiPathUpdater {
  private int VISIBILITY = 3;
  private int INTEREST_LENGTH = 5;

  public void updatePathsToTeams(Graph graph, Set<Team> teams) {
    clearOldPathsToTeams(graph);
    updateGraphVisibilityForTeams(graph, teams);

    for (Team team : teams) {
      updatePathsToTeam(graph, team);
    }
  }

  private void updateGraphVisibilityForTeams(final Graph graph, final Set<Team> teams) {
    for (Team team : teams) {
      Set<Node> nodes = team.units.keySet();
    }
  }

  private void clearOldPathsToTeams(final Graph graph) {
/*    for (Node node : graph.nodeSet) {
      node.shortestEdgeToNextTarget.clear();
      node.visibleForTeam.clear();
    }*/
  }

  public void updatePathsToTeam(Graph graph, Team team) {
    Set<Node> nodes = team.units.keySet();
  }
/*
  private void createEdgeList(Graph<V, E> graph, ClosestFirstIterator<V, E> iter, V startVertex, V endVertex) {
    List<E> edgeList = new ArrayList<E>();

    V v = endVertex;

    while (true) {
      E edge = iter.getSpanningTreeEdge(v);

      if (edge == null) {
        break;
      }

      edgeList.add(edge);
      v = GraphOperator.getOppositeVertex(graph, edge, v);
    }

    Collections.reverse(edgeList);
    double pathLength = iter.getShortestPathLength(endVertex);
    path = new GraphPathImpl<V, E>(graph, startVertex, endVertex, edgeList, pathLength);
  }*/
}
