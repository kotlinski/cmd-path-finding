
package se.kotlinski.graph;

import se.kotlinski.graph.base.Edge;
import se.kotlinski.graph.base.Graph;
import se.kotlinski.graph.base.Node;

import java.util.List;

public interface GraphPath {

  public Graph getGraph();

  public Node getStartVertex();

  public Node getEndVertex();

  public List<Edge> getEdgeList();

  public double getWeight();
}
