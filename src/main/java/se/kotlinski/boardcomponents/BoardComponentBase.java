package se.kotlinski.boardcomponents;

import se.kotlinski.models.Position;
import se.kotlinski.teams.Team;

public abstract class BoardComponentBase {

  public Position position;
  public final Team team;

  public BoardComponentBase(final Position position, final Team team) {
    this.team = team;
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(final Position position) {
    this.position = position;
  }

  public Team getTeam() {
    return team;
  }
}
