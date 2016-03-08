package se.kotlinski.config;

import java.util.ArrayList;
import java.util.List;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.boardcomponents.HeightLevel;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.buildings.BuildingType;
import se.kotlinski.boardcomponents.tiles.Tile;
import se.kotlinski.boardcomponents.tiles.TileType;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.boardcomponents.units.UnitType;
import se.kotlinski.gameboard.GameBoard;
import se.kotlinski.models.Game;
import se.kotlinski.models.Position;
import se.kotlinski.operators.UnitOperator;
import se.kotlinski.teams.Team;

public class PreSetter {

  private static final int HEIGHT = 15;
  private static final int WIDTH = 30;
  private final UnitOperator unitOperator;

  public PreSetter() {
    unitOperator = new UnitOperator();
  }

  public Game createGame() {

    // Todo: change to use HashSet(Position, T)

    Tile[][] tiles = createTiles(WIDTH, HEIGHT);

    Position position = new Position(WIDTH / 2, HEIGHT / 2, HeightLevel.GROUND);
    System.out.println("Width: " + WIDTH);
    System.out.println("Height: " + HEIGHT);
    System.out.println(position);
    System.out.println("tiles.length:");
    System.out.println(tiles.length);
    tiles[HEIGHT / 2][WIDTH / 2] = new Tile(TileType.WATER, position) {
    };
    GameBoard gameBoard = new GameBoard(WIDTH, HEIGHT, tiles);

    List<Building> buildings = new ArrayList<Building>();
    Position baseBuildingPosition = new Position(18, 1, HeightLevel.GROUND_UNIT);
    Building building = new Building(BuildingType.BASE, baseBuildingPosition, Team.TEAM_B);
    buildings.add(building);

    List<Unit> units = new ArrayList<Unit>();
    Position infantryPosition = new Position(0, 5, HeightLevel.GROUND_UNIT);
    units.add(new Unit(UnitType.INFANTERY, Team.TEAM_A, infantryPosition) {
    });

    unitOperator.setBaseTarget(units, Team.TEAM_A, building.getPosition());

    return new Game(units, buildings, gameBoard);
  }

  private Tile[][] createTiles(final int width, final int height) {
    Tile[][] tiles = new Tile[HEIGHT][WIDTH];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Position position = new Position(x, y, HeightLevel.GROUND_UNIT);
        tiles[y][x] = new Tile(TileType.MUD, position) {
        };
      }
    }
    return tiles;
  }

  // Start units

  // End Of Game Controller

}
