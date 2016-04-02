package se.kotlinski.graph.builder;

import se.kotlinski.graph.base.UndirectedGraph;
import se.kotlinski.graph.base.WeightedGraph;


public final class UndirectedWeightedGraphBuilder<V, E, G extends UndirectedGraph<V, E> & WeightedGraph<V, E>>
    extends UndirectedWeightedGraphBuilderBase<V, E, G, UndirectedWeightedGraphBuilder<V, E, G>> {


  public UndirectedWeightedGraphBuilder(G baseGraph) {
    super(baseGraph);
  }

  @Override
  protected UndirectedWeightedGraphBuilder<V, E, G> self() {
    return this;
  }
}


