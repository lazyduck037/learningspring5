package com.taskagile.domain.model.team;

import com.taskagile.domain.model.user.UserId;

import java.util.List;

public interface TeamRepository {
    List<Team> findTeamsByUserId(UserId userId);
    Team findById(TeamId teamId);
    void save(Team team);
}
