

package se.kotlinski.graph.traverse;

import se.kotlinski.graph.Graph;
import se.kotlinski.graph.GraphOperator;
import se.kotlinski.graph.util.FibonacciHeap;
import se.kotlinski.graph.util.FibonacciHeapNode;



public class ClosestFirstIterator<V, E>
    extends CrossComponentIterator<V,
        E, FibonacciHeapNode<ClosestFirstIterator.QueueEntry<V, E>>>
{



    private FibonacciHeap<QueueEntry<V, E>> heap =
        new FibonacciHeap<QueueEntry<V, E>>();


    private double radius = Double.POSITIVE_INFINITY;

    private boolean initialized = false;




    public ClosestFirstIterator(Graph<V, E> g)
    {
        this(g, null);
    }


    public ClosestFirstIterator(Graph<V, E> g, V startVertex)
    {
        this(g, startVertex, Double.POSITIVE_INFINITY);
    }


    public ClosestFirstIterator(Graph<V, E> g, V startVertex, double radius)
    {
        super(g, startVertex);
        this.radius = radius;
        checkRadiusTraversal(isCrossComponentTraversal());
        initialized = true;
    }




    @Override public void setCrossComponentTraversal(
        boolean crossComponentTraversal)
    {
        if (initialized) {
            checkRadiusTraversal(crossComponentTraversal);
        }
        super.setCrossComponentTraversal(crossComponentTraversal);
    }


    public double getShortestPathLength(V vertex)
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = getSeenData(vertex);

        if (node == null) {
            return Double.POSITIVE_INFINITY;
        }

        return node.getKey();
    }


    public E getSpanningTreeEdge(V vertex)
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = getSeenData(vertex);

        if (node == null) {
            return null;
        }

        return node.getData().spanningTreeEdge;
    }


    @Override protected boolean isConnectedComponentExhausted()
    {
        if (heap.size() == 0) {
            return true;
        } else {
            if (heap.min().getKey() > radius) {
                heap.clear();

                return true;
            } else {
                return false;
            }
        }
    }


    @Override protected void encounterVertex(V vertex, E edge)
    {
        double shortestPathLength;
        if (edge == null) {
            shortestPathLength = 0;
        } else {
            shortestPathLength = calculatePathLength(vertex, edge);
        }
        FibonacciHeapNode<QueueEntry<V, E>> node = createSeenData(vertex, edge);
        putSeenData(vertex, node);
        heap.insert(node, shortestPathLength);
    }


    @Override protected void encounterVertexAgain(V vertex, E edge)
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = getSeenData(vertex);

        if (node.getData().frozen) {

            return;
        }

        double candidatePathLength = calculatePathLength(vertex, edge);

        if (candidatePathLength < node.getKey()) {
            node.getData().spanningTreeEdge = edge;
            heap.decreaseKey(node, candidatePathLength);
        }
    }


    @Override protected V provideNextVertex()
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = heap.removeMin();
        node.getData().frozen = true;

        return node.getData().vertex;
    }

    private void assertNonNegativeEdge(E edge)
    {
        if (getGraph().getEdgeWeight(edge) < 0) {
            throw new IllegalArgumentException(
                "negative edge weights not allowed");
        }
    }


    private double calculatePathLength(V vertex, E edge)
    {
        assertNonNegativeEdge(edge);

        V otherVertex = GraphOperator.getOppositeVertex(getGraph(), edge, vertex);
        FibonacciHeapNode<QueueEntry<V, E>> otherEntry =
            getSeenData(otherVertex);

        return otherEntry.getKey()
            + getGraph().getEdgeWeight(edge);
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


