

package se.kotlinski.graph;

import se.kotlinski.graph.base.Edge;
import se.kotlinski.graph.base.Graph;
import se.kotlinski.graph.base.Node;

import java.util.List;



public class GraphPathImpl
    implements GraphPath
{


    private Graph graph;

    private List<Edge> edgeList;

    private Node startVertex;

    private Node endVertex;

    private double weight;



    public GraphPathImpl(
        Graph graph,
        Node startVertex,
        Node endVertex,
        List<Edge> edgeList,
        double weight)
    {
        this.graph = graph;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeList = edgeList;
        this.weight = weight;
    }




    @Override public Graph getGraph()
    {
        return graph;
    }


    @Override public Node getStartVertex()
    {
        return startVertex;
    }


    @Override public Node getEndVertex()
    {
        return endVertex;
    }


    @Override public List<Edge> getEdgeList()
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


