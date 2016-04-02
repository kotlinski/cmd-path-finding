

package se.kotlinski.graph.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.kotlinski.graph.Graph;
import se.kotlinski.graph.GraphOperator;
import se.kotlinski.graph.GraphPath;
import se.kotlinski.graph.GraphPathImpl;
import se.kotlinski.graph.traverse.ClosestFirstIterator;


public final class KotlinskiPaths<V, E> {

  private Graph<V, E> directions;
  private GraphPath<V, E> path;


  public KotlinskiPaths(Graph<V, E> graph, V startVertex, V endVertex) {
    this(graph, startVertex, endVertex, Double.POSITIVE_INFINITY);
  }


  public KotlinskiPaths(Graph<V, E> graph, V startVertex, V endVertex, double radius) {
    if (!graph.containsVertex(endVertex)) {
      throw new IllegalArgumentException("graph must contain the end vertex");
    }

    ClosestFirstIterator<V, E> iter = new ClosestFirstIterator<V, E>(graph, startVertex, radius);

    while (iter.hasNext()) {
      V vertex = iter.next();

      if (vertex.equals(endVertex)) {
        createEdgeList(graph, iter, startVertex, endVertex);
        return;
      }
    }

    path = null;
  }


  public static <V, E> List<E> findPathBetween(Graph<V, E> graph, V startVertex, V endVertex) {
    KotlinskiPaths<V, E> alg = new KotlinskiPaths<V, E>(graph, startVertex, endVertex);

    return alg.getPathEdgeList();
  }


  public List<E> getPathEdgeList() {
    if (path == null) {
      return null;
    } else {
      return path.getEdgeList();
    }
  }


  public GraphPath<V, E> getPath() {
    return path;
  }


  public double getPathLength() {
    if (path == null) {
      return Double.POSITIVE_INFINITY;
    } else {
      return path.getWeight();
    }
  }

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
  }
}


