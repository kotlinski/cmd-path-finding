

package se.kotlinski.graph.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import se.kotlinski.graph.EdgeFactory;
import se.kotlinski.graph.EdgeSetFactory;
import se.kotlinski.graph.Graph;
import se.kotlinski.graph.GraphOperator;
import se.kotlinski.graph.util.ArrayUnenforcedSet;
import se.kotlinski.graph.util.TypeUtil;


public abstract class AbstractBaseGraph<V, E> extends AbstractGraph<V, E>
    implements Graph<V, E>, Cloneable, Serializable {

  private static final long serialVersionUID = -1263088497616142427L;

  private static final String LOOPS_NOT_ALLOWED = "loops not allowed";

  boolean allowingLoops;

  private EdgeFactory<V, E> edgeFactory;
  private EdgeSetFactory<V, E> edgeSetFactory;
  private Map<E, IntrusiveEdge> edgeMap;
  private transient Set<E> unmodifiableEdgeSet = null;
  private transient Set<V> unmodifiableVertexSet = null;
  private Specifics specifics;
  private boolean allowingMultipleEdges;

  private transient TypeUtil<V> vertexTypeDecl = null;


  protected AbstractBaseGraph(EdgeFactory<V, E> ef, boolean allowMultipleEdges, boolean allowLoops) {
    if (ef == null) {
      throw new NullPointerException();
    }

    edgeMap = new LinkedHashMap<E, IntrusiveEdge>();
    edgeFactory = ef;
    allowingLoops = allowLoops;
    allowingMultipleEdges = allowMultipleEdges;

    specifics = createSpecifics();

    this.edgeSetFactory = new ArrayListFactory<V, E>();
  }


  @Override
  public Set<E> getAllEdges(V sourceVertex, V targetVertex) {
    return specifics.getAllEdges(sourceVertex, targetVertex);
  }


  public boolean isAllowingLoops() {
    return allowingLoops;
  }


  public boolean isAllowingMultipleEdges() {
    return allowingMultipleEdges;
  }


  @Override
  public E getEdge(V sourceVertex, V targetVertex) {
    return specifics.getEdge(sourceVertex, targetVertex);
  }


  @Override
  public EdgeFactory<V, E> getEdgeFactory() {
    return edgeFactory;
  }


  public void setEdgeSetFactory(EdgeSetFactory<V, E> edgeSetFactory) {
    this.edgeSetFactory = edgeSetFactory;
  }


  @Override
  public E addEdge(V sourceVertex, V targetVertex) {
    assertVertexExist(sourceVertex);
    assertVertexExist(targetVertex);

    if (!allowingMultipleEdges && containsEdge(sourceVertex, targetVertex)) {
      return null;
    }

    if (!allowingLoops && sourceVertex.equals(targetVertex)) {
      throw new IllegalArgumentException(LOOPS_NOT_ALLOWED);
    }

    E e = edgeFactory.createEdge(sourceVertex, targetVertex);

    if (containsEdge(e)) {

      return null;
    } else {
      IntrusiveEdge intrusiveEdge = createIntrusiveEdge(e, sourceVertex, targetVertex);

      edgeMap.put(e, intrusiveEdge);
      specifics.addEdgeToTouchingVertices(e);

      return e;
    }
  }


  @Override
  public boolean addEdge(V sourceVertex, V targetVertex, E e) {
    if (e == null) {
      throw new NullPointerException();
    } else if (containsEdge(e)) {
      return false;
    }

    assertVertexExist(sourceVertex);
    assertVertexExist(targetVertex);

    if (!allowingMultipleEdges && containsEdge(sourceVertex, targetVertex)) {
      return false;
    }

    if (!allowingLoops && sourceVertex.equals(targetVertex)) {
      throw new IllegalArgumentException(LOOPS_NOT_ALLOWED);
    }

    IntrusiveEdge intrusiveEdge = createIntrusiveEdge(e, sourceVertex, targetVertex);

    edgeMap.put(e, intrusiveEdge);
    specifics.addEdgeToTouchingVertices(e);

    return true;
  }

  private IntrusiveEdge createIntrusiveEdge(E e, V sourceVertex, V targetVertex) {
    IntrusiveEdge intrusiveEdge;
    if (e instanceof IntrusiveEdge) {
      intrusiveEdge = (IntrusiveEdge) e;
    } else {
      intrusiveEdge = new IntrusiveEdge();
    }
    intrusiveEdge.source = sourceVertex;
    intrusiveEdge.target = targetVertex;
    return intrusiveEdge;
  }


  @Override
  public boolean addVertex(V v) {
    if (v == null) {
      throw new NullPointerException();
    } else if (containsVertex(v)) {
      return false;
    } else {
      specifics.addVertex(v);

      return true;
    }
  }


  @Override
  public V getEdgeSource(E e) {
    return TypeUtil.uncheckedCast(getIntrusiveEdge(e).source, vertexTypeDecl);
  }


  @Override
  public V getEdgeTarget(E e) {
    return TypeUtil.uncheckedCast(getIntrusiveEdge(e).target, vertexTypeDecl);
  }

  private IntrusiveEdge getIntrusiveEdge(E e) {
    if (e instanceof IntrusiveEdge) {
      return (IntrusiveEdge) e;
    }

    return edgeMap.get(e);
  }


  @Override
  public Object clone() {
    try {
      TypeUtil<AbstractBaseGraph<V, E>> typeDecl = null;

      AbstractBaseGraph<V, E> newGraph = TypeUtil.uncheckedCast(super.clone(), typeDecl);

      newGraph.edgeMap = new LinkedHashMap<E, IntrusiveEdge>();

      newGraph.edgeFactory = this.edgeFactory;
      newGraph.unmodifiableEdgeSet = null;
      newGraph.unmodifiableVertexSet = null;




      newGraph.specifics = newGraph.createSpecifics();

      GraphOperator.addGraph(newGraph, this);

      return newGraph;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }


  @Override
  public boolean containsEdge(E e) {
    return edgeMap.containsKey(e);
  }


  @Override
  public boolean containsVertex(V v) {
    return specifics.getVertexSet().contains(v);
  }


  public int degreeOf(V vertex) {
    return specifics.degreeOf(vertex);
  }


  @Override
  public Set<E> edgeSet() {
    if (unmodifiableEdgeSet == null) {
      unmodifiableEdgeSet = Collections.unmodifiableSet(edgeMap.keySet());
    }

    return unmodifiableEdgeSet;
  }


  @Override
  public Set<E> edgesOf(V vertex) {
    return specifics.edgesOf(vertex);
  }


  public int inDegreeOf(V vertex) {
    return specifics.inDegreeOf(vertex);
  }


  public Set<E> incomingEdgesOf(V vertex) {
    return specifics.incomingEdgesOf(vertex);
  }


  public int outDegreeOf(V vertex) {
    return specifics.outDegreeOf(vertex);
  }


  public Set<E> outgoingEdgesOf(V vertex) {
    return specifics.outgoingEdgesOf(vertex);
  }


  @Override
  public E removeEdge(V sourceVertex, V targetVertex) {
    E e = getEdge(sourceVertex, targetVertex);

    if (e != null) {
      specifics.removeEdgeFromTouchingVertices(e);
      edgeMap.remove(e);
    }

    return e;
  }


  @Override
  public boolean removeEdge(E e) {
    if (containsEdge(e)) {
      specifics.removeEdgeFromTouchingVertices(e);
      edgeMap.remove(e);

      return true;
    } else {
      return false;
    }
  }


  @Override
  public boolean removeVertex(V v) {
    if (containsVertex(v)) {
      Set<E> touchingEdgesList = edgesOf(v);



      removeAllEdges(new ArrayList<E>(touchingEdgesList));

      specifics.getVertexSet().remove(v);

      return true;
    } else {
      return false;
    }
  }


  @Override
  public Set<V> vertexSet() {
    if (unmodifiableVertexSet == null) {
      unmodifiableVertexSet = Collections.unmodifiableSet(specifics.getVertexSet());
    }

    return unmodifiableVertexSet;
  }


  @Override
  public double getEdgeWeight(E e) {
    if (e instanceof Edge) {
      return ((Edge) e).getWeight();
    } else if (e == null) {
      throw new NullPointerException();
    } else {
      return WeightedGraph.DEFAULT_EDGE_WEIGHT;
    }
  }


  public void setEdgeWeight(E e, double weight) {
    assert (e instanceof Edge) : e.getClass();
    ((Edge) e).weight = weight;
  }

  private Specifics createSpecifics() {
    if (this instanceof UndirectedGraph<?, ?>) {
      return createDirectedSpecifics();
    } else if (this instanceof UndirectedGraph<?, ?>) {
      return createUndirectedSpecifics();
    } else {
      throw new IllegalArgumentException("must be instance of either DirectedGraph or UndirectedGraph");
    }
  }

  protected UndirectedSpecifics createUndirectedSpecifics() {
    return new UndirectedSpecifics();
  }

  protected DirectedSpecifics createDirectedSpecifics() {
    return new DirectedSpecifics();
  }

  private static class ArrayListFactory<VV, EE> implements EdgeSetFactory<VV, EE>, Serializable {
    private static final long serialVersionUID = 5936902837403445985L;


    @Override
    public Set<EE> createEdgeSet(VV vertex) {


      return new ArrayUnenforcedSet<EE>(1);
    }
  }


  protected static class DirectedEdgeContainer<VV, EE> implements Serializable {
    private static final long serialVersionUID = 7494242245729767106L;
    Set<EE> incoming;
    Set<EE> outgoing;
    private transient Set<EE> unmodifiableIncoming = null;
    private transient Set<EE> unmodifiableOutgoing = null;

    DirectedEdgeContainer(EdgeSetFactory<VV, EE> edgeSetFactory, VV vertex) {
      incoming = edgeSetFactory.createEdgeSet(vertex);
      outgoing = edgeSetFactory.createEdgeSet(vertex);
    }


    public Set<EE> getUnmodifiableIncomingEdges() {
      if (unmodifiableIncoming == null) {
        unmodifiableIncoming = Collections.unmodifiableSet(incoming);
      }

      return unmodifiableIncoming;
    }


    public Set<EE> getUnmodifiableOutgoingEdges() {
      if (unmodifiableOutgoing == null) {
        unmodifiableOutgoing = Collections.unmodifiableSet(outgoing);
      }

      return unmodifiableOutgoing;
    }


    public void addIncomingEdge(EE e) {
      incoming.add(e);
    }


    public void addOutgoingEdge(EE e) {
      outgoing.add(e);
    }


    public void removeIncomingEdge(EE e) {
      incoming.remove(e);
    }


    public void removeOutgoingEdge(EE e) {
      outgoing.remove(e);
    }
  }


  private static class UndirectedEdgeContainer<VV, EE> implements Serializable {
    private static final long serialVersionUID = -6623207588411170010L;
    Set<EE> vertexEdges;
    private transient Set<EE> unmodifiableVertexEdges = null;

    UndirectedEdgeContainer(EdgeSetFactory<VV, EE> edgeSetFactory, VV vertex) {
      vertexEdges = edgeSetFactory.createEdgeSet(vertex);
    }


    public Set<EE> getUnmodifiableVertexEdges() {
      if (unmodifiableVertexEdges == null) {
        unmodifiableVertexEdges = Collections.unmodifiableSet(vertexEdges);
      }

      return unmodifiableVertexEdges;
    }


    public void addEdge(EE e) {
      vertexEdges.add(e);
    }


    public int edgeCount() {
      return vertexEdges.size();
    }


    public void removeEdge(EE e) {
      vertexEdges.remove(e);
    }
  }


  private abstract class Specifics implements Serializable {
    private static final long serialVersionUID = 785196247314761183L;

    public abstract void addVertex(V vertex);

    public abstract Set<V> getVertexSet();


    public abstract Set<E> getAllEdges(V sourceVertex, V targetVertex);


    public abstract E getEdge(V sourceVertex, V targetVertex);


    public abstract void addEdgeToTouchingVertices(E e);


    public abstract int degreeOf(V vertex);


    public abstract Set<E> edgesOf(V vertex);


    public abstract int inDegreeOf(V vertex);


    public abstract Set<E> incomingEdgesOf(V vertex);


    public abstract int outDegreeOf(V vertex);


    public abstract Set<E> outgoingEdgesOf(V vertex);


    public abstract void removeEdgeFromTouchingVertices(E e);
  }


  protected class DirectedSpecifics extends Specifics implements Serializable {
    private static final long serialVersionUID = 8971725103718958232L;
    private static final String NOT_IN_DIRECTED_GRAPH = "no such operation in a directed graph";

    protected Map<V, DirectedEdgeContainer<V, E>> vertexMapDirected;

    public DirectedSpecifics() {
      this(new LinkedHashMap<V, DirectedEdgeContainer<V, E>>());
    }

    public DirectedSpecifics(Map<V, DirectedEdgeContainer<V, E>> vertexMap) {
      this.vertexMapDirected = vertexMap;
    }

    @Override
    public void addVertex(V v) {

      vertexMapDirected.put(v, null);
    }

    @Override
    public Set<V> getVertexSet() {
      return vertexMapDirected.keySet();
    }


    @Override
    public Set<E> getAllEdges(V sourceVertex, V targetVertex) {
      Set<E> edges = null;

      if (containsVertex(sourceVertex) && containsVertex(targetVertex)) {
        edges = new ArrayUnenforcedSet<E>();

        DirectedEdgeContainer<V, E> ec = getEdgeContainer(sourceVertex);

        Iterator<E> iter = ec.outgoing.iterator();

        while (iter.hasNext()) {
          E e = iter.next();

          if (getEdgeTarget(e).equals(targetVertex)) {
            edges.add(e);
          }
        }
      }

      return edges;
    }


    @Override
    public E getEdge(V sourceVertex, V targetVertex) {
      if (containsVertex(sourceVertex) && containsVertex(targetVertex)) {
        DirectedEdgeContainer<V, E> ec = getEdgeContainer(sourceVertex);

        Iterator<E> iter = ec.outgoing.iterator();

        while (iter.hasNext()) {
          E e = iter.next();

          if (getEdgeTarget(e).equals(targetVertex)) {
            return e;
          }
        }
      }

      return null;
    }

    @Override
    public void addEdgeToTouchingVertices(E e) {
      V source = getEdgeSource(e);
      V target = getEdgeTarget(e);

      getEdgeContainer(source).addOutgoingEdge(e);
      getEdgeContainer(target).addIncomingEdge(e);
    }


    @Override
    public int degreeOf(V vertex) {
      throw new UnsupportedOperationException(NOT_IN_DIRECTED_GRAPH);
    }


    @Override
    public Set<E> edgesOf(V vertex) {
      ArrayUnenforcedSet<E> inAndOut = new ArrayUnenforcedSet<E>(getEdgeContainer(vertex).incoming);
      inAndOut.addAll(getEdgeContainer(vertex).outgoing);


      if (allowingLoops) {
        Set<E> loops = getAllEdges(vertex, vertex);

        for (int i = 0; i < inAndOut.size();) {
          Object e = inAndOut.get(i);

          if (loops.contains(e)) {
            inAndOut.remove(i);
            loops.remove(e);
          } else {
            i++;
          }
        }
      }

      return Collections.unmodifiableSet(inAndOut);
    }


    @Override
    public int inDegreeOf(V vertex) {
      return getEdgeContainer(vertex).incoming.size();
    }


    @Override
    public Set<E> incomingEdgesOf(V vertex) {
      return getEdgeContainer(vertex).getUnmodifiableIncomingEdges();
    }


    @Override
    public int outDegreeOf(V vertex) {
      return getEdgeContainer(vertex).outgoing.size();
    }


    @Override
    public Set<E> outgoingEdgesOf(V vertex) {
      return getEdgeContainer(vertex).getUnmodifiableOutgoingEdges();
    }

    @Override
    public void removeEdgeFromTouchingVertices(E e) {
      V source = getEdgeSource(e);
      V target = getEdgeTarget(e);

      getEdgeContainer(source).removeOutgoingEdge(e);
      getEdgeContainer(target).removeIncomingEdge(e);
    }


    private DirectedEdgeContainer<V, E> getEdgeContainer(V vertex) {
      assertVertexExist(vertex);

      DirectedEdgeContainer<V, E> ec = vertexMapDirected.get(vertex);

      if (ec == null) {
        ec = new DirectedEdgeContainer<V, E>(edgeSetFactory, vertex);
        vertexMapDirected.put(vertex, ec);
      }

      return ec;
    }
  }


  protected class UndirectedSpecifics extends Specifics implements Serializable {
    private static final long serialVersionUID = 6494588405178655873L;
    private static final String NOT_IN_UNDIRECTED_GRAPH = "no such operation in an undirected graph";

    private Map<V, UndirectedEdgeContainer<V, E>> vertexMapUndirected;

    public UndirectedSpecifics() {
      this(new LinkedHashMap<V, UndirectedEdgeContainer<V, E>>());
    }

    public UndirectedSpecifics(Map<V, UndirectedEdgeContainer<V, E>> vertexMap) {
      this.vertexMapUndirected = vertexMap;
    }

    @Override
    public void addVertex(V v) {

      vertexMapUndirected.put(v, null);
    }

    @Override
    public Set<V> getVertexSet() {
      return vertexMapUndirected.keySet();
    }


    @Override
    public Set<E> getAllEdges(V sourceVertex, V targetVertex) {
      Set<E> edges = null;

      if (containsVertex(sourceVertex) && containsVertex(targetVertex)) {
        edges = new ArrayUnenforcedSet<E>();

        Iterator<E> iter = getEdgeContainer(sourceVertex).vertexEdges.iterator();

        while (iter.hasNext()) {
          E e = iter.next();

          boolean equal = isEqualsStraightOrInverted(sourceVertex, targetVertex, e);

          if (equal) {
            edges.add(e);
          }
        }
      }

      return edges;
    }


    @Override
    public E getEdge(V sourceVertex, V targetVertex) {
      if (containsVertex(sourceVertex) && containsVertex(targetVertex)) {
        Iterator<E> iter = getEdgeContainer(sourceVertex).vertexEdges.iterator();

        while (iter.hasNext()) {
          E e = iter.next();

          boolean equal = isEqualsStraightOrInverted(sourceVertex, targetVertex, e);

          if (equal) {
            return e;
          }
        }
      }

      return null;
    }

    private boolean isEqualsStraightOrInverted(Object sourceVertex, Object targetVertex, E e) {
      boolean equalStraight = sourceVertex.equals(getEdgeSource(e)) && targetVertex.equals(getEdgeTarget(e));

      boolean equalInverted = sourceVertex.equals(getEdgeTarget(e)) && targetVertex.equals(getEdgeSource(e));
      return equalStraight || equalInverted;
    }

    @Override
    public void addEdgeToTouchingVertices(E e) {
      V source = getEdgeSource(e);
      V target = getEdgeTarget(e);

      getEdgeContainer(source).addEdge(e);

      if (!source.equals(target)) {
        getEdgeContainer(target).addEdge(e);
      }
    }

    @Override
    public int degreeOf(V vertex) {
      if (allowingLoops) {

        int degree = 0;
        Set<E> edges = getEdgeContainer(vertex).vertexEdges;

        for (E e : edges) {
          if (getEdgeSource(e).equals(getEdgeTarget(e))) {
            degree += 2;
          } else {
            degree += 1;
          }
        }

        return degree;
      } else {
        return getEdgeContainer(vertex).edgeCount();
      }
    }


    @Override
    public Set<E> edgesOf(V vertex) {
      return getEdgeContainer(vertex).getUnmodifiableVertexEdges();
    }


    @Override
    public int inDegreeOf(V vertex) {
      throw new UnsupportedOperationException(NOT_IN_UNDIRECTED_GRAPH);
    }


    @Override
    public Set<E> incomingEdgesOf(V vertex) {
      throw new UnsupportedOperationException(NOT_IN_UNDIRECTED_GRAPH);
    }


    @Override
    public int outDegreeOf(V vertex) {
      throw new UnsupportedOperationException(NOT_IN_UNDIRECTED_GRAPH);
    }


    @Override
    public Set<E> outgoingEdgesOf(V vertex) {
      throw new UnsupportedOperationException(NOT_IN_UNDIRECTED_GRAPH);
    }

    @Override
    public void removeEdgeFromTouchingVertices(E e) {
      V source = getEdgeSource(e);
      V target = getEdgeTarget(e);

      getEdgeContainer(source).removeEdge(e);

      if (!source.equals(target)) {
        getEdgeContainer(target).removeEdge(e);
      }
    }


    private UndirectedEdgeContainer<V, E> getEdgeContainer(V vertex) {
      assertVertexExist(vertex);

      UndirectedEdgeContainer<V, E> ec = vertexMapUndirected.get(vertex);

      if (ec == null) {
        ec = new UndirectedEdgeContainer<V, E>(edgeSetFactory, vertex);
        vertexMapUndirected.put(vertex, ec);
      }

      return ec;
    }
  }
}


