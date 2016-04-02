package se.kotlinski.graph.builder;

import se.kotlinski.graph.base.UndirectedGraph;


public final class UndirectedGraphBuilder<V, E, G extends UndirectedGraph<V, E>>
    extends UndirectedGraphBuilderBase<V, E, G, UndirectedGraphBuilder<V, E, G>> {


  public UndirectedGraphBuilder(G baseGraph) {
    super(baseGraph);
  }

  @Override
  protected UndirectedGraphBuilder<V, E, G> self() {
    return this;
  }
}


