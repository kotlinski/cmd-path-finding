package se.kotlinski.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.kotlinski.boardcomponents.HeightLevel;
import se.kotlinski.boardcomponents.buildings.Building;
import se.kotlinski.boardcomponents.buildings.BuildingType;
import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.deprecated.controller.Game;
import se.kotlinski.deprecated.controller.GameBoard;
import se.kotlinski.deprecated.controller.graph.base.Node;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;
import se.kotlinski.teams.TeamId;

public class PreSetter {

  private static final int HEIGHT = 6;
  private static final int WIDTH = 30;

  public Game createGame() {
    // Todo: change to use HashSet(Position, T)

    Node[][] tiles = createTiles(WIDTH, HEIGHT);

    Position position1 = new Position(WIDTH / 2, HEIGHT / 2, HeightLevel.GROUND);
    Position position2 = new Position(WIDTH / 2, HEIGHT / 2 + 1, HeightLevel.GROUND);
    Position position3 = new Position(WIDTH / 2 + 1, HEIGHT / 2, HeightLevel.GROUND);
    tiles[position1.y][position1.x] = new Node(HEIGHT / 2, WIDTH / 2) {
    };
    tiles[position2.y][position2.x] = new Node(HEIGHT / 2 + 1, WIDTH / 2) {
    };
    tiles[position3.y][position3.x] = new Node(HEIGHT / 2, WIDTH / 2 + 1) {
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
/*

    unitOperator.setBaseUnitTarget(teamA, teamB.baseBuilding);
    unitOperator.setBaseUnitTarget(teamB, teamA.baseBuilding);

*/
    return new Game(teams, buildings, gameBoard);
  }

  private Team setUpTeamA() {
    TeamId teamA = TeamId.TEAM_A;
    Position baseBuildingPosition = new Position(2, 2, HeightLevel.GROUND_UNIT);
    Building baseBuilding = new Building(BuildingType.BASE, teamA);
    HashMap<Node, Unit> units = new HashMap<>();

    Position infantryPosition = new Position(baseBuildingPosition.x + 1, baseBuildingPosition.y,
        HeightLevel.GROUND_UNIT);
/*    units.put(infantryPosition, new Unit(UnitType.INFANTRY, teamA, infantryPosition) {
    });*/

    return new Team(baseBuilding);
  }

  private Team setUpTeamB() {
    TeamId teamB = TeamId.TEAM_B;
    Position baseBuildingPosition = new Position(WIDTH - 2, HEIGHT - 2, HeightLevel.GROUND_UNIT);
    Building baseBuilding = new Building(BuildingType.BASE, teamB);
    HashMap<Node, Unit> units = new HashMap<>();

    Position infantryPosition = new Position(baseBuildingPosition.x - 1, baseBuildingPosition.y,
        HeightLevel.GROUND_UNIT);
/*    units.put(infantryPosition, new Unit(UnitType.INFANTRY, teamB, infantryPosition) {
    });*/
    return new Team(baseBuilding);
  }

  private Node[][] createTiles(final int width, final int height) {
    Node[][] tiles = new Node[HEIGHT][WIDTH];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Position position = new Position(x, y, HeightLevel.GROUND_UNIT);
        tiles[y][x] = new Node(y, x) {
        };
      }
    }
    return tiles;
  }

  // Start units

  // End Of Game Controller

}
