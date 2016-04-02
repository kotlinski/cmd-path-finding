

package se.kotlinski.graph;

import java.util.List;



public interface GraphPath<V, E>
{



    public Graph<V, E> getGraph();


    public V getStartVertex();


    public V getEndVertex();


    public List<E> getEdgeList();


    public double getWeight();
}


