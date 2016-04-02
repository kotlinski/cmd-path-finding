package se.kotlinski.graph.builder;

import se.kotlinski.graph.base.UndirectedGraph;


public abstract class UndirectedGraphBuilderBase<V, E, G extends UndirectedGraph<V, E>, B extends UndirectedGraphBuilderBase<V, E, G, B>>
    extends AbstractGraphBuilder<V, E, G, B> {


  public UndirectedGraphBuilderBase(G baseGraph) {
    super(baseGraph);
  }

}


