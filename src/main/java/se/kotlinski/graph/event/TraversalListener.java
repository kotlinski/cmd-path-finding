

package se.kotlinski.graph.event;


public interface TraversalListener<V, E>
{



    public void connectedComponentFinished(ConnectedComponentTraversalEvent e);


    public void connectedComponentStarted(ConnectedComponentTraversalEvent e);


    public void edgeTraversed(EdgeTraversalEvent<V, E> e);


    public void vertexTraversed(VertexTraversalEvent<V> e);


    public void vertexFinished(VertexTraversalEvent<V> e);
}


