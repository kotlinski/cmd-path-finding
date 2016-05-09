package se.kotlinski.pathfinding;

import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.boardcomponents.units.UnitType;
import se.kotlinski.config.data.BoardGameMap;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public class TeamVisibilityAccessorTest {

  private BoardGameMap boardGameMap;
  private TeamVisibilityAccessor teamVisibilityAccessor;

  @Before
  public void setUp() throws Exception {
    /*MapImporter mapImporter = new MapImporter();
    boardGameMap = mapImporter.importMap("test_map_a");
*/
    teamVisibilityAccessor = new TeamVisibilityAccessor();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void calculateVisiblePositionsForTeam() throws Exception {
    Unit unit = new Unit(UnitType.INFANTRY, TeamId.TEAM_A){};
    Map<Position, Unit> units = new HashMap<>();
    units.put(new Position(4, 4), unit);
    TeamId teamId = TeamId.TEAM_A;
    Set<Position> positions = teamVisibilityAccessor.calculateVisiblePositionsForTeam(units, teamId);

    Set<Position> correctPositions = new HashSet<>();
    correctPositions.add(new Position(4, 4));

    correctPositions.add(new Position(4-1, 4));
    correctPositions.add(new Position(4-2, 4));
    correctPositions.add(new Position(4+1, 4));
    correctPositions.add(new Position(4+2, 4));

    correctPositions.add(new Position(4,4-1));
    correctPositions.add(new Position(4,4+1));
    correctPositions.add(new Position(4,4-2));
    correctPositions.add(new Position(4,4+2));

    correctPositions.add(new Position(4+1,4-1));
    correctPositions.add(new Position(4+1,4+1));
    correctPositions.add(new Position(4-1,4-1));
    correctPositions.add(new Position(4-1,4+1));

    assertEquals(correctPositions, positions);

  }

}