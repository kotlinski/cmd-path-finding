package se.kotlinski.gameboard;

import se.kotlinski.boardcomponents.tiles.Tile;

public class GameBoard {

  public final int WIDTH;
  public final int HEIGHT;
  public final Tile[][] tiles;

  public final String[][] BASE_MATRIX;

  public GameBoard(final int width, final int height, Tile[][] tiles) {
    WIDTH = width;
    HEIGHT = height;
    this.tiles = tiles;

    BASE_MATRIX = new String[HEIGHT][WIDTH];
  }
}
