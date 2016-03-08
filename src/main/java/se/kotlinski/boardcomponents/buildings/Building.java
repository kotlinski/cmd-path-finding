package se.kotlinski.boardcomponents.buildings;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

public class Building extends BoardComponentBase {

  private final BuildingType buildingType;

  public Building(final BuildingType buildingType, final Position position, final Team team) {
    super(position, team);
    this.buildingType = buildingType;
  }

  public BuildingType getBuildingType() {
    return buildingType;
  }
}
