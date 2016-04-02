package se.kotlinski.graph.builder;

import se.kotlinski.graph.GraphOperator;
import se.kotlinski.graph.base.UndirectedGraph;
import se.kotlinski.graph.base.WeightedGraph;


public abstract class UndirectedWeightedGraphBuilderBase<V, E, G extends UndirectedGraph<V, E> & WeightedGraph<V, E>, B extends UndirectedWeightedGraphBuilderBase<V, E, G, B>>
    extends UndirectedGraphBuilderBase<V, E, G, B> {


  public UndirectedWeightedGraphBuilderBase(G baseGraph) {
    super(baseGraph);
  }


  public B addEdge(V source, V target, double weight) {
    GraphOperator.addEdgeWithVertices(this.graph, source, target, weight);
    return this.self();
  }
}


