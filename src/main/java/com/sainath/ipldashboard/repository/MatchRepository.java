package com.sainath.ipldashboard.repository;

import com.sainath.ipldashboard.entity.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTeam1OrTeam2OrderByMatchDateDesc(String teamName1, String teamName2, Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName, Integer count) {
        Pageable pageable = PageRequest.of(0, count);
        return findByTeam1OrTeam2OrderByMatchDateDesc(teamName, teamName, pageable);
    }
}
