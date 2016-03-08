package se.kotlinski.gameboard.cmd;

import java.util.List;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.tiles.Tile;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.GameBoard;
import se.kotlinski.models.Game;
import se.kotlinski.models.Position;

public class GameBoardDrawer {

  private final Game game;
  private final CharTable charTable;
  private String[][] canvas;
  private ColorTable colorTable;

  public GameBoardDrawer(final Game game) {
    this.game = game;
    colorTable = new ColorTable();
    charTable = new CharTable();
  }

  public void drawGameboard() {
    canvas = game.gameBoard.BASE_MATRIX;
    appendTiles(canvas, game.gameBoard);
    appendStaticItems(canvas);
    appendItems();
    appendBuildings(canvas, game.buildings);
    drawUnits(canvas, game.units);

    render(canvas);
  }

  private void appendTiles(final String[][] canvas, final GameBoard gameBoard) {
    for (int y = 0; y < gameBoard.HEIGHT; y++) {
      for (int x = 0; x < gameBoard.WIDTH; x++) {
        canvas[y][x] = getTileDrawable(gameBoard.tiles[y][x]);
      }
    }
  }

  private String getTileDrawable(final Tile tile) {
    return colorTable.getColorForTile(tile) + charTable.getCharForTile(tile) + AnsiColor.ANSI_RESET;
  }

  private void appendStaticItems(final String[][] base_matrix) {
    // remove?
  }

  private void appendBuildings(final String[][] base_matrix, final List<Building> buildings) {
    for (BoardComponentBase building : buildings) {
      Position position = building.getPosition();
      base_matrix[position.y][position.x] = charTable.getCharForBuilding(building);
    }
  }

  private void drawUnits(final String[][] base_matrix, final List<Unit> units) {
    for (Unit unit : units) {
      Position position = unit.getPosition();
      base_matrix[position.y][position.x] = getUnitDrawable(unit);
    }
  }

  private String getUnitDrawable(final Unit unit) {
    return colorTable.getColorForUnit(unit) + charTable.getCharForUnit(unit) + AnsiColor.ANSI_RESET;
  }

  private void appendItems() {
    // Nothing to do for now
  }

  private void render(final String[][] base_matrix) {
    StringBuilder stringBuilder = new StringBuilder();
    for (String[] chars : base_matrix) {
      stringBuilder.append("\t");
      for (String aChar : chars) {
        stringBuilder.append(aChar);
      }
      stringBuilder.append('\n');
    }
    String s = stringBuilder.toString();

    final String ANSI_CLS = "\u001b[2J";
    final String ANSI_HOME = "\u001b[H";
    System.out.print(ANSI_CLS + ANSI_HOME);
    System.out.flush();

    System.out.print(s);

  }
}
