package com.sainath.ipldashboard.api;

import com.sainath.ipldashboard.entity.Match;
import com.sainath.ipldashboard.entity.Team;
import com.sainath.ipldashboard.model.ApiResponse;
import com.sainath.ipldashboard.service.MatchService;
import com.sainath.ipldashboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teams")
public class TeamRestController {

    private final TeamService teamService;
    private final MatchService matchService;

    @Autowired
    public TeamRestController(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Team>>> getAllTeams() {
        return ResponseEntity.ok(new ApiResponse<>(teamService.findAll(), "success"));
    }

    @GetMapping("/{teamName}")
    public ResponseEntity<ApiResponse<Team>> getTeam(@PathVariable String teamName) {
        Team team = teamService.findByName(teamName);
        return ResponseEntity.ok(new ApiResponse<>(team, "success"));
    }

    @GetMapping("/{teamName}/matches")
    public ResponseEntity<ApiResponse<List<Match>>> getMatchesForTeam(@PathVariable String teamName, @RequestParam Integer year) {
        List<Match> matches = matchService.findByTeamAndYear(teamName, year);
        return ResponseEntity.ok(new ApiResponse<>(matches, "success"));
    }
}
