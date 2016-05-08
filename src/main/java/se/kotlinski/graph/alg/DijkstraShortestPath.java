package se.kotlinski.graph.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.kotlinski.graph.GraphPath;
import se.kotlinski.graph.base.Edge;
import se.kotlinski.graph.base.Graph;
import se.kotlinski.graph.base.Node;
import se.kotlinski.graph.traverse.ClosestFirstIterator;

public final class DijkstraShortestPath<V, E> {

  private GraphPath path;

  public DijkstraShortestPath(Graph graph, V startVertex, V endVertex) {
    this(graph, startVertex, endVertex, Double.POSITIVE_INFINITY);
  }

  public DijkstraShortestPath(Graph graph, V startVertex, V endVertex, double radius) {
    /*if (!graph.containsNode(endVertex)) {
      throw new IllegalArgumentException("graph must contain the end vertex");
    }

    ClosestFirstIterator<Node, Edge> iter = new ClosestFirstIterator(graph, startVertex, radius);

    while (iter.hasNext()) {
      V vertex = iter.next();

      if (vertex.equals(endVertex)) {
        createEdgeList(graph, iter, startVertex, endVertex);
        return;
      }
    }

    path = null;*/
  }

  public static List<Edge> findPathBetween(Graph graph, Node startVertex, Node endVertex) {
    DijkstraShortestPath alg = new DijkstraShortestPath(graph, startVertex, endVertex);

    return alg.getPathEdgeList();
  }

  public List<Edge> getPathEdgeList() {
    if (path == null) {
      return null;
    } else {
      return path.getEdgeList();
    }
  }

  public GraphPath getPath() {
    return path;
  }

  public double getPathLength() {
    if (path == null) {
      return Double.POSITIVE_INFINITY;
    } else {
      return path.getWeight();
    }
  }

  private void createEdgeList(Graph graph, ClosestFirstIterator<V, E> iter, V startVertex, V endVertex) {
    /*List<E> edgeList = new ArrayList<E>();

    V v = endVertex;

    while (true) {
      E edge = iter.getSpanningTreeEdge(v);

      if (edge == null) {
        break;
      }

      edgeList.add(edge);
     // v = GraphOperator.getOppositeVertex(graph, edge, v);
    }

    Collections.reverse(edgeList);
    double pathLength = iter.getShortestPathLength(endVertex);*/
    // path = new GraphPathImpl<V, E>(graph, startVertex, endVertex, edgeList, pathLength);
  }
}
