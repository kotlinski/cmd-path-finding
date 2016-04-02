

package se.kotlinski.graph;


public interface EdgeFactory<V, E>
{



    public E createEdge(V sourceVertex, V targetVertex);
}


