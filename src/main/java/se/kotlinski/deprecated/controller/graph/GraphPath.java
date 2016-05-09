
package se.kotlinski.deprecated.controller.graph;

import se.kotlinski.deprecated.controller.graph.base.Edge;
import se.kotlinski.deprecated.controller.graph.base.Graph;
import se.kotlinski.deprecated.controller.graph.base.Node;

import java.util.List;

public interface GraphPath {

  public Graph getGraph();

  public Node getStartVertex();

  public Node getEndVertex();

  public List<Edge> getEdgeList();

  public double getWeight();
}
