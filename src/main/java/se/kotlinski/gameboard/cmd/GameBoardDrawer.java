package se.kotlinski.gameboard.cmd;

import java.util.List;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.gameboard.GameBoard;
import se.kotlinski.graph.base.Node;
import se.kotlinski.models.Game;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

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
    drawUnits(canvas, game.teams);

    render(canvas);
  }

  private void appendTiles(final String[][] canvas, final GameBoard gameBoard) {
    for (int y = 0; y < gameBoard.HEIGHT; y++) {
      for (int x = 0; x < gameBoard.WIDTH; x++) {
        canvas[y][x] = getTileDrawable(gameBoard.tiles[y][x]);
      }
    }
  }

  private String getTileDrawable(final Node tile) {
    return colorTable.getColorForTile(tile) + charTable.getCharForTile(tile) + AnsiColor.ANSI_RESET;
  }

  private void appendStaticItems(final String[][] base_matrix) {
    // remove?
  }

  private void appendBuildings(final String[][] base_matrix, final List<Building> buildings) {
    for (BoardComponentBase building : buildings) {
      Position position = building.getPosition();
      base_matrix[position.y][position.x] = getBuildingDrawable(building);
    }
  }

  private String getBuildingDrawable(final BoardComponentBase building) {
    return colorTable.getTeamColor(building) + charTable.getCharForBuilding(building) + AnsiColor.ANSI_RESET;
  }

  private void drawUnits(final String[][] base_matrix, final List<Team> teams) {
    for (Team team : teams) {
      for (Unit unit : team.units.values()) {
        Position position = unit.getPosition();
        base_matrix[position.y][position.x] = getUnitDrawable(unit);
      }
    }
  }

  private String getUnitDrawable(final Unit unit) {
    return colorTable.getTeamColor(unit) + charTable.getCharForUnit(unit) + AnsiColor.ANSI_RESET;
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
