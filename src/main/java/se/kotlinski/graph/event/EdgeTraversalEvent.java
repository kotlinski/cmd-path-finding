

package se.kotlinski.graph.event;

import java.util.EventObject;



public class EdgeTraversalEvent<V, E>
    extends EventObject
{


    private static final long serialVersionUID = 4050768173789820979L;




    protected E edge;




    public EdgeTraversalEvent(Object eventSource, E edge)
    {
        super(eventSource);
        this.edge = edge;
    }




    public E getEdge()
    {
        return edge;
    }
}


