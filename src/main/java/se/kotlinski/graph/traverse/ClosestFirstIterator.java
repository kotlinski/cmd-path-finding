

package se.kotlinski.graph.traverse;


import se.kotlinski.graph.base.Graph;
import se.kotlinski.graph.util.FibonacciHeap;
import se.kotlinski.graph.util.FibonacciHeapNode;



public class ClosestFirstIterator<V, E>
{



    private FibonacciHeap<QueueEntry<V, E>> heap =
        new FibonacciHeap<QueueEntry<V, E>>();


    private double radius = Double.POSITIVE_INFINITY;

    private boolean initialized = false;




    public ClosestFirstIterator(Graph g)
    {
        this(g, null);
    }


    public ClosestFirstIterator(Graph g, V startVertex)
    {
        this(g, startVertex, Double.POSITIVE_INFINITY);
    }


    public ClosestFirstIterator(Graph g, V startVertex, double radius)
    {
        this.radius = radius;
        initialized = true;
    }




    private double calculatePathLength(V vertex, E edge)
    {
    /*    assertNonNegativeEdge(edge);

        V otherVertex = GraphOperator.getOppositeVertex(getGraph(), edge, vertex);
        FibonacciHeapNode<QueueEntry<V, E>> otherEntry =
            getSeenData(otherVertex);

        return otherEntry.getKey()
            + getGraph().getEdgeWeight(edge);*/
      return 1.0;
    }

    private void checkRadiusTraversal(boolean crossComponentTraversal)
    {
        if (crossComponentTraversal && (radius != Double.POSITIVE_INFINITY)) {
            throw new IllegalArgumentException(
                "radius may not be specified for cross-component traversal");
        }
    }


    private FibonacciHeapNode<QueueEntry<V, E>> createSeenData(
        V vertex,
        E edge)
    {
        QueueEntry<V, E> entry = new QueueEntry<V, E>();
        entry.vertex = vertex;
        entry.spanningTreeEdge = edge;

        return new FibonacciHeapNode<QueueEntry<V, E>>(entry);
    }




    static class QueueEntry<V, E>
    {

        E spanningTreeEdge;


        V vertex;


        boolean frozen;

        QueueEntry()
        {
        }
    }
}


