

package se.kotlinski.graph;

import java.util.Set;



public interface EdgeSetFactory<V, E>
{



    public Set<E> createEdgeSet(V vertex);
}


