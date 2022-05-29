package com.sainath.ipldashboard.repository;

import com.sainath.ipldashboard.entity.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTeam1OrTeam2OrderByMatchDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("SELECT m FROM Match m where (m.team1 = :teamName or m.team2 = :teamName) " +
            "AND m.matchDate between :startDate AND :endDate ORDER BY m.matchDate DESC")
    List<Match> findMatchesByTeamBetweenDate(@Param("teamName") String teamName,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

    default List<Match> findLatestMatchesByTeam(String teamName, Integer count) {
        Pageable pageable = PageRequest.of(0, count);
        return findByTeam1OrTeam2OrderByMatchDateDesc(teamName, teamName, pageable);
    }
}
