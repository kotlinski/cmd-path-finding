package se.kotlinski.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.kotlinski.graph.base.UndirectedGraph;
import se.kotlinski.graph.base.WeightedGraph;

public abstract class GraphOperator {

  public static <Node, Edge> Edge addEdge(Graph<Node, Edge> graph, Node nodeA, Node nodeB, double weight) {
    EdgeFactory<Node, Edge> edgeFactory = graph.getEdgeFactory();
    Edge edge = edgeFactory.createEdge(nodeA, nodeB);




    assert (graph instanceof WeightedGraph<?, ?>) : graph.getClass();
    ((WeightedGraph<Node, Edge>) graph).setEdgeWeight(edge, weight);

    return graph.addEdge(nodeA, nodeB, edge) ? edge : null;
  }

  public static <Node, Edge> Edge addEdgeWithVertices(Graph<Node, Edge> g, Node sourceVertex, Node targetVertex) {
    g.addVertex(sourceVertex);
    g.addVertex(targetVertex);

    return g.addEdge(sourceVertex, targetVertex);
  }

  public static <Node, Edge> boolean addEdgeWithVertices(Graph<Node, Edge> targetGraph, Graph<Node, Edge> sourceGraph,
      Edge edge) {
    Node sourceVertex = sourceGraph.getEdgeSource(edge);
    Node targetVertex = sourceGraph.getEdgeTarget(edge);

    targetGraph.addVertex(sourceVertex);
    targetGraph.addVertex(targetVertex);

    return targetGraph.addEdge(sourceVertex, targetVertex, edge);
  }

  public static <Node, Edge> Edge addEdgeWithVertices(Graph<Node, Edge> g, Node sourceVertex, Node targetVertex,
      double weight) {
    g.addVertex(sourceVertex);
    g.addVertex(targetVertex);

    return addEdge(g, sourceVertex, targetVertex, weight);
  }



  public static <Node, Edge> boolean addAllEdges(Graph<? super Node, ? super Edge> destination,
      Graph<Node, Edge> source, Collection<? extends Edge> edges) {
    boolean modified = false;

    for (Edge edge : edges) {
      Node s = source.getEdgeSource(edge);
      Node t = source.getEdgeTarget(edge);
      destination.addVertex(s);
      destination.addVertex(t);
      modified |= destination.addEdge(s, t, edge);
    }

    return modified;
  }

  public static <Node, Edge> boolean addAllVertices(Graph<? super Node, ? super Edge> destination,
      Collection<? extends Node> vertices) {
    boolean modified = false;

    for (Node node : vertices) {
      modified |= destination.addVertex(node);
    }

    return modified;
  }

  public static <Node, Edge> List<Node> neighborListOf(Graph<Node, Edge> g, Node node) {
    List<Node> neighbors = new ArrayList<Node>();

    for (Edge edge : g.edgesOf(node)) {
      neighbors.add(getOppositeVertex(g, edge, node));
    }

    return neighbors;
  }

  public static <Node, Edge> UndirectedGraph<Node, Edge> undirectedGraph(Graph<Node, Edge> graph) {
    if (graph instanceof UndirectedGraph<?, ?>) {
      return (UndirectedGraph<Node, Edge>) graph;
    } else {
      throw new IllegalArgumentException("Graph must be either UndirectedGraph or UndirectedGraph");
    }
  }

  public static <Node, Edge> boolean testIncidence(Graph<Node, Edge> g, Edge edge, Node node) {
    return (g.getEdgeSource(edge).equals(node)) || (g.getEdgeTarget(edge).equals(node));
  }

  public static <Node, Edge> Node getOppositeVertex(Graph<Node, Edge> g, Edge edge, Node node) {
    Node source = g.getEdgeSource(edge);
    Node target = g.getEdgeTarget(edge);
    if (node.equals(source)) {
      return target;
    } else if (node.equals(target)) {
      return source;
    } else {
      throw new IllegalArgumentException("no such node: " + node.toString());
    }
  }

  public static <Node, Edge> List<Node> getPathVertexList(GraphPath<Node, Edge> path) {
    Graph<Node, Edge> g = path.getGraph();
    List<Node> list = new ArrayList<Node>();
    Node node = path.getStartVertex();
    list.add(node);
    for (Edge edge : path.getEdgeList()) {
      node = getOppositeVertex(g, edge, node);
      list.add(node);
    }
    return list;
  }
}
