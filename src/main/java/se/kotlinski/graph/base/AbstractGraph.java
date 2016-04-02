

package se.kotlinski.graph.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import se.kotlinski.graph.Graph;
import se.kotlinski.graph.util.TypeUtil;


public abstract class AbstractGraph<V, E> implements Graph<V, E> {


  protected AbstractGraph() {
  }


  @Override
  public boolean containsEdge(V sourceVertex, V targetVertex) {
    return getEdge(sourceVertex, targetVertex) != null;
  }


  @Override
  public boolean removeAllEdges(Collection<? extends E> edges) {
    boolean modified = false;

    for (E e : edges) {
      modified |= removeEdge(e);
    }

    return modified;
  }


  @Override
  public Set<E> removeAllEdges(V sourceVertex, V targetVertex) {
    Set<E> removed = getAllEdges(sourceVertex, targetVertex);
    if (removed == null) {
      return null;
    }
    removeAllEdges(removed);

    return removed;
  }


  @Override
  public boolean removeAllVertices(Collection<? extends V> vertices) {
    boolean modified = false;

    for (V v : vertices) {
      modified |= removeVertex(v);
    }

    return modified;
  }


  @Override
  public String toString() {
    return toStringFromSets(vertexSet(), edgeSet(), (this instanceof UndirectedGraph<?, ?>));
  }


  protected boolean assertVertexExist(V v) {
    if (containsVertex(v)) {
      return true;
    } else if (v == null) {
      throw new NullPointerException();
    } else {
      throw new IllegalArgumentException("no such vertex in graph: " + v.toString());
    }
  }


  protected boolean removeAllEdges(E[] edges) {
    boolean modified = false;

    for (int i = 0; i < edges.length; i++) {
      modified |= removeEdge(edges[i]);
    }

    return modified;
  }


  protected String toStringFromSets(Collection<? extends V> vertexSet, Collection<? extends E> edgeSet,
      boolean directed) {
    List<String> renderedEdges = new ArrayList<String>();

    StringBuffer sb = new StringBuffer();
    for (E e : edgeSet) {
      if (e.getClass() != Edge.class) {
        sb.append(e.toString());
        sb.append("=");
      }
      if (directed) {
        sb.append("(");
      } else {
        sb.append("{");
      }
      sb.append(getEdgeSource(e));
      sb.append(",");
      sb.append(getEdgeTarget(e));
      if (directed) {
        sb.append(")");
      } else {
        sb.append("}");
      }


      renderedEdges.add(sb.toString());
      sb.setLength(0);
    }

    return "(" + vertexSet + ", " + renderedEdges + ")";
  }


  @Override
  public int hashCode() {
    int hash = vertexSet().hashCode();

    for (E e : edgeSet()) {
      int part = e.hashCode();

      int source = getEdgeSource(e).hashCode();
      int target = getEdgeTarget(e).hashCode();


      int pairing = ((source + target) * (source + target + 1) / 2) + target;
      part = (27 * part) + pairing;

      long weight = (long) getEdgeWeight(e);
      part = (27 * part) + (int) (weight ^ (weight >>> 32));

      hash += part;
    }

    return hash;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }

    TypeUtil<Graph<V, E>> typeDecl = null;
    Graph<V, E> g = TypeUtil.uncheckedCast(obj, typeDecl);

    if (!vertexSet().equals(g.vertexSet())) {
      return false;
    }
    if (edgeSet().size() != g.edgeSet().size()) {
      return false;
    }

    for (E e : edgeSet()) {
      V source = getEdgeSource(e);
      V target = getEdgeTarget(e);

      if (!g.containsEdge(e)) {
        return false;
      }

      if (!g.getEdgeSource(e).equals(source) || !g.getEdgeTarget(e).equals(target)) {
        return false;
      }

      if (Math.abs(getEdgeWeight(e) - g.getEdgeWeight(e)) > 10e-7) {
        return false;
      }
    }

    return true;
  }
}


