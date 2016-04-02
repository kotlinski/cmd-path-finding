

package se.kotlinski.graph.base;

import se.kotlinski.graph.Graph;


public interface UndirectedGraph<V, E>
    extends Graph<V, E>
{



    public int degreeOf(V vertex);
}


