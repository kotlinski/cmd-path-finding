package se.kotlinski.boardcomponents;

import se.kotlinski.teams.TeamId;

public abstract class BoardComponentBase {

  public final TeamId teamId;

  public BoardComponentBase(TeamId teamId) {
    this.teamId = teamId;
  }

  public TeamId getTeamId() {
    return teamId;
  }
}
