package se.kotlinski.graph.base;

import se.kotlinski.graph.EdgeFactory;
import se.kotlinski.graph.builder.UndirectedWeightedGraphBuilder;
import se.kotlinski.graph.builder.UndirectedWeightedGraphBuilderBase;

public class SimpleWeightedGraph<Node, Edge> extends SimpleGraph<Node, Edge> implements WeightedGraph<Node, Edge> {

  public SimpleWeightedGraph(EdgeFactory<Node, Edge> ef) {
    super(ef);
  }

  public SimpleWeightedGraph(Class<? extends Edge> edgeClass) {
    this(new ClassBasedEdgeFactory<Node, Edge>(edgeClass));
  }

  public static <Node, Edge> UndirectedWeightedGraphBuilderBase<Node, Edge, ? extends SimpleWeightedGraph<Node, Edge>, ?> builder(
      Class<? extends Edge> edgeClass) {
    return new UndirectedWeightedGraphBuilder<Node, Edge, SimpleWeightedGraph<Node, Edge>>(
        new SimpleWeightedGraph<Node, Edge>(edgeClass));
  }

  public static <Node, Edge> UndirectedWeightedGraphBuilderBase<Node, Edge, ? extends SimpleWeightedGraph<Node, Edge>, ?> builder(
      EdgeFactory<Node, Edge> ef) {
    return new UndirectedWeightedGraphBuilder<Node, Edge, SimpleWeightedGraph<Node, Edge>>(
        new SimpleWeightedGraph<Node, Edge>(ef));
  }
}


