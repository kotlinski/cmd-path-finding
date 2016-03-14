
package se.kotlinski.boardcomponents.graph;

/**
 * An interface for a graph whose edges have non-uniform weights.
 *
 * @author Barak Naveh
 * @since Jul 23, 2003
 */
public interface WeightedGraph<V, E>
    extends Graph<V, E>
{


    /**
     * The default weight for an edge.
     */
    public static double DEFAULT_EDGE_WEIGHT = 1.0;



    /**
     * Assigns a weight to an edge.
     *
     * @param e edge on which to set weight
     * @param weight new weight for edge
     */
    public void setEdgeWeight(E e, double weight);
}

// End WeightedGraph.java
