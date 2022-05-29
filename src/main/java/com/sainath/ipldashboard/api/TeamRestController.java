package com.sainath.ipldashboard.api;

import com.sainath.ipldashboard.entity.Team;
import com.sainath.ipldashboard.model.ApiResponse;
import com.sainath.ipldashboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/teams")
public class TeamRestController {

    private final TeamService teamService;

    @Autowired
    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{teamName}")
    public ResponseEntity<ApiResponse<Team>> getTeam(@PathVariable String teamName) {
        Team team = teamService.findByName(teamName);
        return ResponseEntity.ok(new ApiResponse<>(team, "success"));
    }
}
