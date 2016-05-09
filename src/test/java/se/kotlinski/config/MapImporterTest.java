package se.kotlinski.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kotlinski.config.data.BoardGameMap;

import static junit.framework.TestCase.assertEquals;

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
    assertEquals(boardGameMap.getMapAttributes().getHeight(), 40);
    assertEquals(boardGameMap.getMapAttributes().getHeight(), 20);

    assertEquals(boardGameMap.getUnits().size(), 1);
  }

}