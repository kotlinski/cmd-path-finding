package se.kotlinski.gameboard;

import se.kotlinski.graph.base.Node;

public class GameBoard {

  public final int WIDTH;
  public final int HEIGHT;
  public final Node[][] tiles;

  public final String[][] BASE_MATRIX;

  public GameBoard(final int width, final int height, Node[][] tiles) {
    WIDTH = width;
    HEIGHT = height;
    this.tiles = tiles;

    BASE_MATRIX = new String[HEIGHT][WIDTH];
  }
}
