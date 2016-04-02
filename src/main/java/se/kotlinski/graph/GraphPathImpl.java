

package se.kotlinski.graph;

import java.util.List;



public class GraphPathImpl<V, E>
    implements GraphPath<V, E>
{


    private Graph<V, E> graph;

    private List<E> edgeList;

    private V startVertex;

    private V endVertex;

    private double weight;



    public GraphPathImpl(
        Graph<V, E> graph,
        V startVertex,
        V endVertex,
        List<E> edgeList,
        double weight)
    {
        this.graph = graph;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeList = edgeList;
        this.weight = weight;
    }




    @Override public Graph<V, E> getGraph()
    {
        return graph;
    }


    @Override public V getStartVertex()
    {
        return startVertex;
    }


    @Override public V getEndVertex()
    {
        return endVertex;
    }


    @Override public List<E> getEdgeList()
    {
        return edgeList;
    }


    @Override public double getWeight()
    {
        return weight;
    }


    @Override public String toString()
    {
        return edgeList.toString();
    }
}


