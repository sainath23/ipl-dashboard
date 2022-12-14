package com.sainath.ipldashboard.batchjob;

import com.sainath.ipldashboard.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        log.info("!!! JOB FINISHED! Time to verify the results");

        Map<String, Team> teamMap = new HashMap<>();

        entityManager.createQuery("SELECT m.team1, count(m) FROM Match m GROUP BY m.team1", Object[].class)
                .getResultList()
                .stream()
                .map(rs -> new Team((String) rs[0], (long) rs[1]))
                .forEach(team -> teamMap.put(team.getTeamName(), team));

        entityManager.createQuery("SELECT m.team2, count(m) FROM Match m GROUP BY m.team2", Object[].class)
                .getResultList()
                .forEach(rs -> {
                    Team team = teamMap.get((String) rs[0]);
                    team.setTotalMatches(team.getTotalMatches() + (long) rs[1]);
                });

        entityManager.createQuery("SELECT m.matchWinner, count(m) FROM Match m GROUP BY m.matchWinner", Object[].class)
                .getResultList()
                .forEach(rs -> {
                    Team team = teamMap.get((String) rs[0]);
                    if (team != null) team.setTotalWins((long) rs[1]);
                });

        teamMap.values().forEach(entityManager::persist);

        entityManager.createQuery("SELECT t FROM Team t", Team.class)
                .getResultList()
                .forEach(team -> log.info(team.toString()));
    }
}
