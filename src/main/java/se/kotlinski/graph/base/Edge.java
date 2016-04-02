

package se.kotlinski.graph.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Edge implements Cloneable, Serializable {

  Set<Node> nodes;

  double weight = WeightedGraph.DEFAULT_EDGE_WEIGHT;

  public Edge() {
    this.nodes = new HashSet<Node>();
  }

  protected double getWeight() {
    return weight;
  }

  public Set<Node> getNodes() {
    return nodes;
  }

  @Override
  public String toString() {
    String objectString = "(";
    for (Object node : nodes) {
      objectString += node + ", ";
    }
    return objectString + ")";
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Edge that = (Edge) o;

    return Double.compare(that.weight, weight) == 0 &&
           (nodes != null ? nodes.equals(that.nodes) : that.nodes == null);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = nodes != null ? nodes.hashCode() : 0;
    temp = Double.doubleToLongBits(weight);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public Object clone() {
    try {
      return super.clone();
    }
    catch (CloneNotSupportedException e) {

      throw new InternalError();
    }
  }

}
