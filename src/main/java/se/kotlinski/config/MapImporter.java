package se.kotlinski.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import se.kotlinski.boardcomponents.HeightLevel;
import se.kotlinski.boardcomponents.units.UnitType;
import se.kotlinski.graph.base.Graph;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public class MapImporter {

  public Graph importMap(String folderName) throws IOException {

    Graph map = new Graph(10, 10);

    addUnitsToMap(map, folderName);

    System.out.println(map);

    return map;
  }

  private Iterable<CSVRecord> addUnitsToMap(Graph map, String folderName) throws IOException {
    InputStream fstream = getClass().getClassLoader().getResourceAsStream(folderName + File.separator + "units.csv");
    BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

    Iterable<CSVRecord> records = getCsvRecords(folderName + File.separator + "units.csv");
    for (CSVRecord record : records) {
      UnitType unitType = UnitType.valueOf(record.get("unit"));
      TeamId teamId = TeamId.valueOf(record.get("team"));
      int x = new Integer(record.get("x"));
      int y = new Integer(record.get("y"));
      Position position = new Position(x, y, HeightLevel.GROUND_UNIT);
      map.addUnitToGame(unitType, teamId, position);
      //Unit unit = new Unit(unitType, position, teamId){};
    }

    return CSVFormat.DEFAULT.parse(in);
  }


  private Iterable<CSVRecord> getCsvRecords(String folderName) throws IOException {
    InputStream fstream = getClass().getClassLoader().getResourceAsStream(folderName);
    BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
    return CSVFormat.DEFAULT.withHeader().parse(in);
  }

}
