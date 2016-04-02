package se.kotlinski.graph.base;

import se.kotlinski.graph.EdgeFactory;
import se.kotlinski.graph.builder.UndirectedGraphBuilder;
import se.kotlinski.graph.builder.UndirectedGraphBuilderBase;


public class SimpleGraph<V, E> extends AbstractBaseGraph<V, E> implements UndirectedGraph<V, E> {

  private static final long serialVersionUID = 3545796589454112304L;


  public SimpleGraph(EdgeFactory<V, E> ef) {
    super(ef, false, false);
  }


  public SimpleGraph(Class<? extends E> edgeClass) {
    this(new ClassBasedEdgeFactory<V, E>(edgeClass));
  }

  public static <V, E> UndirectedGraphBuilderBase<V, E, ? extends SimpleGraph<V, E>, ?> builder(
      Class<? extends E> edgeClass) {
    return new UndirectedGraphBuilder<V, E, SimpleGraph<V, E>>(new SimpleGraph<V, E>(edgeClass));
  }

  public static <V, E> UndirectedGraphBuilderBase<V, E, ? extends SimpleGraph<V, E>, ?> builder(EdgeFactory<V, E> ef) {
    return new UndirectedGraphBuilder<V, E, SimpleGraph<V, E>>(new SimpleGraph<V, E>(ef));
  }
}


