package se.kotlinski.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kotlinski.graph.base.Graph;

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
    Graph map = mapImporter.importMap("test_map_a");
  }

}