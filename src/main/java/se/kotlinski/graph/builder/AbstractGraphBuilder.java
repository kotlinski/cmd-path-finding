package se.kotlinski.graph.builder;

import se.kotlinski.graph.Graph;
import se.kotlinski.graph.GraphOperator;


public abstract class AbstractGraphBuilder<V, E, G extends Graph<V, E>, B extends AbstractGraphBuilder<V, E, G, B>> {

  protected final G graph;


  public AbstractGraphBuilder(G baseGraph) {
    this.graph = baseGraph;
  }


  protected abstract B self();


  public B addVertex(V vertex) {
    this.graph.addVertex(vertex);
    return this.self();
  }


  public B addVertices(V... vertices) {
    for (V vertex : vertices) {
      this.addVertex(vertex);
    }
    return this.self();
  }


  public B addEdge(V source, V target) {
    GraphOperator.addEdgeWithVertices(this.graph, source, target);
    return this.self();
  }


  public B addEdgeChain(V first, V second, V... rest) {
    this.addEdge(first, second);
    V last = second;
    for (V vertex : rest) {
      this.addEdge(last, vertex);
      last = vertex;
    }
    return this.self();
  }


  public B addGraph(Graph<? extends V, ? extends E> sourceGraph) {
    GraphOperator.addGraph(this.graph, sourceGraph);
    return this.self();
  }


  public B removeVertex(V vertex) {
    this.graph.removeVertex(vertex);
    return this.self();
  }


  public B removeVertices(V... vertices) {
    for (V vertex : vertices) {
      this.removeVertex(vertex);
    }
    return this.self();
  }


  public B removeEdge(V source, V target) {
    this.graph.removeEdge(source, target);
    return this.self();
  }


  public G build() {
    return this.graph;
  }
}


