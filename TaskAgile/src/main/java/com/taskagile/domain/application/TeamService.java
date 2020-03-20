package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.team.TeamId;
import com.taskagile.domain.model.user.UserId;

import java.util.List;

public interface TeamService {
    List<Team> findTeamsByUserId(UserId userId);
    Team findById(TeamId teamId);
    Team createTeam(CreateTeamCommand command);
}
