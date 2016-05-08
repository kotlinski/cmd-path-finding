package se.kotlinski.boardcomponents;

import se.kotlinski.models.Position;
import se.kotlinski.teams.TeamId;

public abstract class BoardComponentBase {

  public Position position;
  public final TeamId teamId;

  public BoardComponentBase(final TeamId teamId, Position position) {
    this.teamId = teamId;
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

  public TeamId getTeamId() {
    return teamId;
  }
}
