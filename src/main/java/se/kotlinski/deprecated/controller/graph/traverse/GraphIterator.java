

package se.kotlinski.deprecated.controller.graph.traverse;

import java.util.Iterator;

import se.kotlinski.deprecated.controller.graph.event.TraversalListener;


public interface GraphIterator<V, E> extends Iterator<V> {


  public boolean isCrossComponentTraversal();


  public boolean isReuseEvents();


  public void setReuseEvents(boolean reuseEvents);


  public void addTraversalListener(TraversalListener<V, E> l);


  @Override
  public void remove();


  public void removeTraversalListener(TraversalListener<V, E> l);
}


