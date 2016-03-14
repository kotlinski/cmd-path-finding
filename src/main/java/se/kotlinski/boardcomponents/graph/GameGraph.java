package se.kotlinski.boardcomponents.graph;

public class GameGraph {

  GameGraph<String, DefaultWeightedEdge> g = create();

  protected Graph<String, DefaultWeightedEdge> createWithBias(boolean negate) {
    Graph<String, DefaultWeightedEdge> g;
    double bias = 1;

    g = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

    g.addVertex(V1);
    g.addVertex(V2);
    g.addVertex(V3);
    g.addVertex(V4);
    g.addVertex(V5);

    e12 = Graphs.addEdge(g, V1, V2, bias * 2);

    e13 = Graphs.addEdge(g, V1, V3, bias * 3);

    e24 = Graphs.addEdge(g, V2, V4, bias * 5);

    e34 = Graphs.addEdge(g, V3, V4, bias * 20);

    e45 = Graphs.addEdge(g, V4, V5, bias * 5);

    e15 = Graphs.addEdge(g, V1, V5, bias * 100);

    return g;
  }

}
