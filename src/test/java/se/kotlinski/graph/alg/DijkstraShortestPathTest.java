package se.kotlinski.graph.alg;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import se.kotlinski.graph.base.Edge;
import se.kotlinski.graph.base.Graph;

public class DijkstraShortestPathTest {

  static final String NODE_Y0_X0 = "Y0_X0";
  static final String NODE_Y0_X1 = "Y0_X1";
  static final String NODE_Y0_X2 = "Y0_X2";
  static final String NODE_Y0_X3 = "Y0_X3";
  static final String NODE_Y0_X4 = "Y0_X4";
  static final String NODE_Y1_X0 = "Y1_X0";

  @Test
  public void testConstructor() {
    DijkstraShortestPath<String, Edge> path;
    Graph graph = createWithBias(20, 15);

    path = new DijkstraShortestPath<String, Edge>(graph, NODE_Y0_X3, NODE_Y0_X2,
                                                  Double.POSITIVE_INFINITY);

   // Edge edgeBetween = graph.getEdge(NODE_Y0_X2, NODE_Y0_X3);

    // Todo: should also work with:
    //Edge edgeBetween = graph.getEdge(NODE_Y0_X3, NODE_Y0_X2);

    assertEquals(Arrays.asList(new Edge[] {

    }), path.getPathEdgeList());

    assertEquals(5.0, path.getPathLength(), 0);
    /*
     * path = new DijkstraShortestPath<String, Edge>(graph,
     * NODE_Y0_X2, NODE_Y0_X3, 7); assertNull(path.getPathEdgeList());
     * assertEquals(Double.POSITIVE_INFINITY, path.getPathLength(), 0);
     */
  }

  public void testPathBetween() {
    /*
     * List path; Graph<String, Edge> graph = createWithBias();
     *
     * path = findPathBetween(graph, NODE_Y0_X0, NODE_Y0_X1);
     * assertEquals(Arrays.asList(new Edge[] { edge_00_01 }), path);
     *
     * path = findPathBetween(graph, NODE_Y0_X0, NODE_Y0_X3);
     * assertEquals(Arrays.asList(new Edge[] { edge_00_01, edge_01_02,
     * edge_02_03 }), path);
     *
     * path = findPathBetween(graph, NODE_Y0_X0, NODE_Y0_X4);
     * assertEquals(Arrays.asList(new Edge[] { edge_00_01, edge_01_02,
     * edge_02_03, edge_03_04 }), path);
     *
     * path = findPathBetween(graph, NODE_Y1_X0, NODE_Y0_X3);
     * assertEquals(Arrays.asList(new Edge[] { edge_00_10, edge_01_02,
     * edge_00_01, edge_03_04 }), path);
     */
  }

  protected List findPathBetween(Graph g, String src, String dest) {
    return null;  }

  protected Graph createWithBias(final int width, final int height) {

   /* Graph graph;
    double bias = 1;
    graph = new Graph();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        String node_name = "Y" + y + "_X" + x;
        graph.addNode(node_name);
      }
    }

    // Connect nodes horizontal
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width - 1; x++) {
        GraphOperator.addEdge(graph, "Y" + y + "_X" + x, "Y" + y + "_X" + (x + 1), bias * 5);
      }
    }

    // Connect nodes vertically
    for (int y = 0; y < height - 1; y++) {
      for (int x = 0; x < width; x++) {
        GraphOperator.addEdge(graph, "Y" + y + "_X" + x, "Y" + (y + 1) + "_X" + x, bias * 5);
      }
    }
*/
    return null;
  }

}
