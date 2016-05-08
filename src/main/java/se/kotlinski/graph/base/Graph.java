
package se.kotlinski.graph.base;

import java.util.HashMap;
import java.util.Map;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.boardcomponents.units.UnitType;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;
import se.kotlinski.teams.TeamId;

public class Graph {

  public final Node[][] gameBoard;
  private Node[][] defaultValues;

  private Map<TeamId, Team> teams;

  public Graph(int width, int height) {
    gameBoard = new Node[height][width];
    setDefaultValues(gameBoard);

    teams = new HashMap<>();
  }

  public void addNode(Node node, int x, int y) {
    gameBoard[x][y] = node; // will throw if out of bounds
  }

  @Override
  public String toString() {

    for (Node[] nodes : gameBoard) {
      for (Node node : nodes) {
        System.out.print(node);
        System.out.print(" ");
      }
      System.out.println(" ");
    }

    return super.toString();
  }

  public void setDefaultValues(Node[][] nodeMatrix) {
    int y = 0;
    for (Node[] nodes : nodeMatrix) {
      for (int x = 0; x < nodes.length; x++) {
        nodes[x] = new Node(y, x);
      }
      y++;
    }
    this.defaultValues = defaultValues;
  }

  public void addUnitToGame(UnitType unitType, TeamId teamId, Position position) {
    Unit unit = new Unit(unitType, position, teamId) {
    };

    Team team = teams.get(teamId);
    team.units.add(unit);

    gameBoard[position.x][position.y].units.add(unit);
  }

}
