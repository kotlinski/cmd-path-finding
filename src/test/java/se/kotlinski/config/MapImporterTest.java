package se.kotlinski.config;

import static junit.framework.TestCase.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.boardcomponents.units.UnitType;
import se.kotlinski.config.data.BoardGameMap;
import se.kotlinski.config.data.MapAttributes;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public class MapImporterTest {


  MapImporter mapImporter;

  @Before
  public void setUp() throws Exception {
    mapImporter = new MapImporter();
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void importMapFromCSV() throws Exception {
    BoardGameMap boardGameMap = mapImporter.importMap("test_map_a");
    MapAttributes mapAttributes = new MapAttributes(40, 20);
    Map<Position, Unit> units = new HashMap();
    units.put(new Position(4, 4), new Unit(UnitType.INFANTRY, TeamId.TEAM_A) {});
    BoardGameMap expectedGameMap = new BoardGameMap(mapAttributes, units);
    assertEquals(boardGameMap, expectedGameMap);
  }

}
