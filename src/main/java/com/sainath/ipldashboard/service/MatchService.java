package com.sainath.ipldashboard.service;

import com.sainath.ipldashboard.entity.Match;
import com.sainath.ipldashboard.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Match> findByTeamAndYear(String teamName, Integer year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);

        return matchRepository.findMatchesByTeamBetweenDate(teamName, startDate, endDate);
    }
}
