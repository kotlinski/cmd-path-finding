package se.kotlinski.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import se.kotlinski.teams.TeamType;

public class PreSetter {

  private static final int HEIGHT = 6;
  private static final int WIDTH = 30;
  private final UnitOperator unitOperator;

  public PreSetter() {
    unitOperator = new UnitOperator();
  }

  public Game createGame() {
    // Todo: change to use HashSet(Position, T)

    Tile[][] tiles = createTiles(WIDTH, HEIGHT);

    Position position1 = new Position(WIDTH / 2, HEIGHT / 2, HeightLevel.GROUND);
    Position position2 = new Position(WIDTH / 2, HEIGHT / 2 + 1, HeightLevel.GROUND);
    Position position3 = new Position(WIDTH / 2 + 1, HEIGHT / 2, HeightLevel.GROUND);
    tiles[position1.y][position1.x] = new Tile(TileType.WATER, position1) {
    };
    tiles[position2.y][position2.x] = new Tile(TileType.WATER, position2) {
    };
    tiles[position3.y][position3.x] = new Tile(TileType.WATER, position3) {
    };
    GameBoard gameBoard = new GameBoard(WIDTH, HEIGHT, tiles);

    Team teamA = setUpTeamA();
    Team teamB = setUpTeamB();
    List<Team> teams = new ArrayList<Team>();
    teams.add(teamA);
    teams.add(teamB);

    List<Building> buildings = new ArrayList<Building>();
    buildings.add(teamA.baseBuilding);
    buildings.add(teamB.baseBuilding);

    unitOperator.setBaseUnitTarget(teamA, teamB.baseBuilding);
    unitOperator.setBaseUnitTarget(teamB, teamA.baseBuilding);

    return new Game(teams, buildings, gameBoard);
  }

  private Team setUpTeamA() {
    TeamType teamA = TeamType.TEAM_A;
    Position baseBuildingPosition = new Position(2, 2, HeightLevel.GROUND_UNIT);
    Building baseBuilding = new Building(BuildingType.BASE, baseBuildingPosition, teamA);
    HashMap<Position, Unit> units = new HashMap<Position, Unit>();

    Position infantryPosition = new Position(baseBuildingPosition.x + 1, baseBuildingPosition.y,
        HeightLevel.GROUND_UNIT);
    units.put(infantryPosition, new Unit(UnitType.INFANTERY, teamA, infantryPosition) {
    });

    return new Team(baseBuilding, units);
  }

  private Team setUpTeamB() {
    TeamType teamB = TeamType.TEAM_B;
    Position baseBuildingPosition = new Position(WIDTH - 2, HEIGHT - 2, HeightLevel.GROUND_UNIT);
    Building baseBuilding = new Building(BuildingType.BASE, baseBuildingPosition, teamB);
    HashMap<Position, Unit> units = new HashMap<Position, Unit>();

    Position infantryPosition = new Position(baseBuildingPosition.x - 1, baseBuildingPosition.y,
        HeightLevel.GROUND_UNIT);
    units.put(infantryPosition, new Unit(UnitType.INFANTERY, teamB, infantryPosition) {
    });
    return new Team(baseBuilding, units);
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
