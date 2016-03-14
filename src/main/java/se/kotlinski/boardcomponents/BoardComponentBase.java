package se.kotlinski.boardcomponents;

import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamType;

public abstract class BoardComponentBase {

  public Position position;
  public final TeamType teamType;

  public BoardComponentBase(final Position position, final TeamType teamType) {
    this.teamType = teamType;
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(final Position position) {
    this.position.y = position.y;
    this.position.x = position.x;
    this.position.heightLevel = position.heightLevel;
  }

  public TeamType getTeamType() {
    return teamType;
  }
}
