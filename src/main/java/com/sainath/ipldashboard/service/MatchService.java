package com.sainath.ipldashboard.service;

import com.sainath.ipldashboard.entity.Match;
import com.sainath.ipldashboard.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findByTeam(String teamName) {
        return matchRepository.findLatestMatchesByTeam(teamName, 4);
    }
}
