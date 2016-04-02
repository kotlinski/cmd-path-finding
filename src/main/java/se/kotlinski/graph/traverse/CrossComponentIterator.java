

package se.kotlinski.graph.traverse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import se.kotlinski.graph.Graph;
import se.kotlinski.graph.GraphOperator;
import se.kotlinski.graph.base.UndirectedGraph;
import se.kotlinski.graph.event.ConnectedComponentTraversalEvent;
import se.kotlinski.graph.event.EdgeTraversalEvent;
import se.kotlinski.graph.event.VertexTraversalEvent;


public abstract class CrossComponentIterator<V, E, D> extends AbstractGraphIterator<V, E> {

  private static final int CCS_BEFORE_COMPONENT = 1;
  private static final int CCS_WITHIN_COMPONENT = 2;
  private static final int CCS_AFTER_COMPONENT = 3;

  private final ConnectedComponentTraversalEvent ccFinishedEvent = new ConnectedComponentTraversalEvent(this,
      ConnectedComponentTraversalEvent.CONNECTED_COMPONENT_FINISHED);
  private final ConnectedComponentTraversalEvent ccStartedEvent = new ConnectedComponentTraversalEvent(this,
      ConnectedComponentTraversalEvent.CONNECTED_COMPONENT_STARTED);
  private final Graph<V, E> graph;


  private FlyweightEdgeEvent<V, E> reusableEdgeEvent;
  private FlyweightVertexEvent<V> reusableVertexEvent;
  private Iterator<V> vertexIterator = null;


  private Map<V, D> seen = new HashMap<V, D>();
  private V startVertex;
  private Specifics<V, E> specifics;

  private int state = CCS_BEFORE_COMPONENT;


  public CrossComponentIterator(Graph<V, E> g, V startVertex) {
    super();

    if (g == null) {
      throw new IllegalArgumentException("graph must not be null");
    }
    graph = g;

    specifics = createGraphSpecifics(g);
    vertexIterator = g.vertexSet().iterator();
    setCrossComponentTraversal(startVertex == null);

    reusableEdgeEvent = new FlyweightEdgeEvent<V, E>(this, null);
    reusableVertexEvent = new FlyweightVertexEvent<V>(this, null);

    if (startVertex == null) {

      if (vertexIterator.hasNext()) {
        this.startVertex = vertexIterator.next();
      } else {
        this.startVertex = null;
      }
    } else if (g.containsVertex(startVertex)) {
      this.startVertex = startVertex;
    } else {
      throw new IllegalArgumentException("graph must contain the start vertex");
    }
  }


  static <V, E> Specifics<V, E> createGraphSpecifics(Graph<V, E> g) {
    if (g instanceof UndirectedGraph<?, ?>) {
      return new UndirectedSpecifics<V, E>((UndirectedGraph<V, E>) g);
    } else {
      return new UndirectedSpecifics<V, E>(g);
    }
  }


  public Graph<V, E> getGraph() {
    return graph;
  }


  @Override
  public boolean hasNext() {
    if (startVertex != null) {
      encounterStartVertex();
    }

    if (isConnectedComponentExhausted()) {
      if (state == CCS_WITHIN_COMPONENT) {
        state = CCS_AFTER_COMPONENT;
        if (nListeners != 0) {
          fireConnectedComponentFinished(ccFinishedEvent);
        }
      }

      if (isCrossComponentTraversal()) {
        while (vertexIterator.hasNext()) {
          V v = vertexIterator.next();

          if (!isSeenVertex(v)) {
            encounterVertex(v, null);
            state = CCS_BEFORE_COMPONENT;

            return true;
          }
        }

        return false;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }


  @Override
  public V next() {
    if (startVertex != null) {
      encounterStartVertex();
    }

    if (hasNext()) {
      if (state == CCS_BEFORE_COMPONENT) {
        state = CCS_WITHIN_COMPONENT;
        if (nListeners != 0) {
          fireConnectedComponentStarted(ccStartedEvent);
        }
      }

      V nextVertex = provideNextVertex();
      if (nListeners != 0) {
        fireVertexTraversed(createVertexTraversalEvent(nextVertex));
      }

      addUnseenChildrenOf(nextVertex);

      return nextVertex;
    } else {
      throw new NoSuchElementException();
    }
  }


  protected abstract boolean isConnectedComponentExhausted();


  protected abstract void encounterVertex(V vertex, E edge);


  protected abstract V provideNextVertex();


  protected D getSeenData(V vertex) {
    return seen.get(vertex);
  }


  protected boolean isSeenVertex(Object vertex) {
    return seen.containsKey(vertex);
  }


  protected abstract void encounterVertexAgain(V vertex, E edge);


  protected D putSeenData(V vertex, D data) {
    return seen.put(vertex, data);
  }


  protected void finishVertex(V vertex) {
    if (nListeners != 0) {
      fireVertexFinished(createVertexTraversalEvent(vertex));
    }
  }



  private void addUnseenChildrenOf(V vertex) {
    for (E edge : specifics.edgesOf(vertex)) {
      if (nListeners != 0) {
        fireEdgeTraversed(createEdgeTraversalEvent(edge));
      }

      V oppositeV = GraphOperator.getOppositeVertex(graph, edge, vertex);

      if (isSeenVertex(oppositeV)) {
        encounterVertexAgain(oppositeV, edge);
      } else {
        encounterVertex(oppositeV, edge);
      }
    }
  }

  private EdgeTraversalEvent<V, E> createEdgeTraversalEvent(E edge) {
    if (isReuseEvents()) {
      reusableEdgeEvent.setEdge(edge);

      return reusableEdgeEvent;
    } else {
      return new EdgeTraversalEvent<V, E>(this, edge);
    }
  }

  private VertexTraversalEvent<V> createVertexTraversalEvent(V vertex) {
    if (isReuseEvents()) {
      reusableVertexEvent.setVertex(vertex);

      return reusableVertexEvent;
    } else {
      return new VertexTraversalEvent<V>(this, vertex);
    }
  }

  private void encounterStartVertex() {
    encounterVertex(startVertex, null);
    startVertex = null;
  }


  protected static enum VisitColor {

    WHITE,


    GRAY,


    BLACK
  }

  static interface SimpleContainer<T> {

    public boolean isEmpty();


    public void add(T o);


    public T remove();
  }


  abstract static class Specifics<VV, EE> {

    public abstract Set<? extends EE> edgesOf(VV vertex);
  }


  static class FlyweightEdgeEvent<VV, localE> extends EdgeTraversalEvent<VV, localE> {
    private static final long serialVersionUID = 4051327833765000755L;

    public FlyweightEdgeEvent(Object eventSource, localE edge) {
      super(eventSource, edge);
    }


    protected void setEdge(localE edge) {
      this.edge = edge;
    }
  }


  static class FlyweightVertexEvent<VV> extends VertexTraversalEvent<VV> {
    private static final long serialVersionUID = 3834024753848399924L;


    public FlyweightVertexEvent(Object eventSource, VV vertex) {
      super(eventSource, vertex);
    }


    protected void setVertex(VV vertex) {
      this.vertex = vertex;
    }
  }


  private static class UndirectedSpecifics<VV, EE> extends Specifics<VV, EE> {
    private Graph<VV, EE> graph;


    public UndirectedSpecifics(Graph<VV, EE> g) {
      graph = g;
    }


    @Override
    public Set<EE> edgesOf(VV vertex) {
      return graph.edgesOf(vertex);
    }
  }
}


