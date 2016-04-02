

package se.kotlinski.graph.traverse;

import java.util.ArrayList;
import java.util.List;

import se.kotlinski.graph.event.ConnectedComponentTraversalEvent;
import se.kotlinski.graph.event.EdgeTraversalEvent;
import se.kotlinski.graph.event.TraversalListener;
import se.kotlinski.graph.event.VertexTraversalEvent;


public abstract class AbstractGraphIterator<V, E> implements GraphIterator<V, E> {




  protected int nListeners = 0;
  private List<TraversalListener<V, E>> traversalListeners = new ArrayList<TraversalListener<V, E>>();
  private boolean crossComponentTraversal = true;
  private boolean reuseEvents = false;


  @Override
  public boolean isCrossComponentTraversal() {
    return crossComponentTraversal;
  }


  public void setCrossComponentTraversal(boolean crossComponentTraversal) {
    this.crossComponentTraversal = crossComponentTraversal;
  }


  @Override
  public boolean isReuseEvents() {
    return reuseEvents;
  }


  @Override
  public void setReuseEvents(boolean reuseEvents) {
    this.reuseEvents = reuseEvents;
  }


  @Override
  public void addTraversalListener(TraversalListener<V, E> l) {
    if (!traversalListeners.contains(l)) {
      traversalListeners.add(l);
      nListeners = traversalListeners.size();
    }
  }


  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }


  @Override
  public void removeTraversalListener(TraversalListener<V, E> l) {
    traversalListeners.remove(l);
    nListeners = traversalListeners.size();
  }


  protected void fireConnectedComponentFinished(ConnectedComponentTraversalEvent e) {
    for (int i = 0; i < nListeners; i++) {
      TraversalListener<V, E> l = traversalListeners.get(i);
      l.connectedComponentFinished(e);
    }
  }


  protected void fireConnectedComponentStarted(ConnectedComponentTraversalEvent e) {
    for (int i = 0; i < nListeners; i++) {
      TraversalListener<V, E> l = traversalListeners.get(i);
      l.connectedComponentStarted(e);
    }
  }


  protected void fireEdgeTraversed(EdgeTraversalEvent<V, E> e) {
    for (int i = 0; i < nListeners; i++) {
      TraversalListener<V, E> l = traversalListeners.get(i);
      l.edgeTraversed(e);
    }
  }


  protected void fireVertexTraversed(VertexTraversalEvent<V> e) {
    for (int i = 0; i < nListeners; i++) {
      TraversalListener<V, E> l = traversalListeners.get(i);
      l.vertexTraversed(e);
    }
  }


  protected void fireVertexFinished(VertexTraversalEvent<V> e) {
    for (int i = 0; i < nListeners; i++) {
      TraversalListener<V, E> l = traversalListeners.get(i);
      l.vertexFinished(e);
    }
  }
}


