

package se.kotlinski.graph.base;

import se.kotlinski.graph.Graph;


public interface WeightedGraph<V, E> extends Graph<V, E> {


  public static double DEFAULT_EDGE_WEIGHT = 1.0;


  public void setEdgeWeight(E e, double weight);
}


