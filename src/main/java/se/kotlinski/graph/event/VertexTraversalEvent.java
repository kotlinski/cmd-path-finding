

package se.kotlinski.graph.event;

import java.util.EventObject;



public class VertexTraversalEvent<V>
    extends EventObject
{


    private static final long serialVersionUID = 3688790267213918768L;




    protected V vertex;




    public VertexTraversalEvent(Object eventSource, V vertex)
    {
        super(eventSource);
        this.vertex = vertex;
    }




    public V getVertex()
    {
        return vertex;
    }
}


