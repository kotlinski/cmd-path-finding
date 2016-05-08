package se.kotlinski.boardcomponents.buildings;

import se.kotlinski.boardcomponents.BoardComponentBase;
import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public class Building extends BoardComponentBase {

  private final BuildingType buildingType;

  public Building(final BuildingType buildingType, final Position position, final TeamId teamId) {
    super(position, teamId);
    this.buildingType = buildingType;
  }

  public BuildingType getBuildingType() {
    return buildingType;
  }
}
