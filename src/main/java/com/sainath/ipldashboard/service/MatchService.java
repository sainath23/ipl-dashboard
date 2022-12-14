package com.sainath.ipldashboard.service;

import com.sainath.ipldashboard.entity.Match;
import com.sainath.ipldashboard.repository.MatchRepository;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public MatchService(MatchRepository matchRepository, JobLauncher jobLauncher, Job job) {
        this.matchRepository = matchRepository;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public List<Match> findByTeam(String teamName) {
        return matchRepository.findLatestMatchesByTeam(teamName, 4);
    }

    public List<Match> findByTeamAndYear(String teamName, Integer year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);

        return matchRepository.findMatchesByTeamBetweenDate(teamName, startDate, endDate);
    }

    public BatchStatus runImportUserJob() {
        JobParameters jobParameters = new JobParameters();
        JobExecution jobExecution;
        try {
            jobExecution = jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }

        return jobExecution.getStatus();
    }
}
