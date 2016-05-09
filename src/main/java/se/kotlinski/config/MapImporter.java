package se.kotlinski.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import se.kotlinski.boardcomponents.units.Unit;
import se.kotlinski.boardcomponents.units.UnitType;
import se.kotlinski.config.data.BoardGameMap;
import se.kotlinski.config.data.MapAttributes;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public class MapImporter {

  public BoardGameMap importMap(String folderName) throws IOException {
    MapAttributes mapAttributes = importMapAttributes(folderName);
    Map<Position, Unit> units = importUnitAttributes(folderName);

    // TODO read in buildings.

    return new BoardGameMap(mapAttributes, units);
  }

  private MapAttributes importMapAttributes(String folderName) throws IOException {
    Iterable<CSVRecord> records = getCsvRecords(folderName + File.separator + "map.csv");
    for (CSVRecord record : records) {
      int width = Integer.parseInt(record.get("width"));
      int height = Integer.parseInt(record.get("height"));
      return new MapAttributes(width, height);
    }
    throw new RuntimeException("Invalid map.csv");
  }

  private Map<Position, Unit> importUnitAttributes(String folderName) throws IOException {
    Map<Position, Unit> units = new HashMap();
    Iterable<CSVRecord> records = getCsvRecords(folderName + File.separator + "units.csv");
    for (CSVRecord record : records) {
      UnitType unitType = UnitType.valueOf(record.get("unit"));
      TeamId teamId = TeamId.valueOf(record.get("team"));
      int x = new Integer(record.get("x"));
      int y = new Integer(record.get("y"));
      Position position = new Position(x, y);
      Unit unit = new Unit(unitType, teamId){};
      units.put(position, unit);
    }
    return units;
  }


  private Iterable<CSVRecord> getCsvRecords(String folderName) throws IOException {
    InputStream fstream = getClass().getClassLoader().getResourceAsStream(folderName);
    BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
    return CSVFormat.DEFAULT.withHeader().parse(in);
  }

}
