package com.sainath.ipldashboard.service;

import com.sainath.ipldashboard.entity.Match;
import com.sainath.ipldashboard.entity.Team;
import com.sainath.ipldashboard.exception.TeamNotFoundException;
import com.sainath.ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchService matchService;

    @Autowired
    public TeamService(TeamRepository teamRepository, MatchService matchService) {
        this.teamRepository = teamRepository;
        this.matchService = matchService;
    }

    public Team findByName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName).orElseThrow(() -> new TeamNotFoundException("Team with name " + teamName + " not found!"));
        List<Match> matches = matchService.findByTeam(team.getTeamName());
        team.setMatches(matches);
        return team;
    }
}
