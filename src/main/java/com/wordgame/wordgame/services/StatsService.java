package com.wordgame.wordgame.services;

import com.wordgame.wordgame.dao.StatsDAO;
import com.wordgame.wordgame.domain.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatsService {

    private final StatsDAO statsDAO;

    @Autowired
    public StatsService(StatsDAO statsDAO) {
        this.statsDAO = statsDAO;
    }

    public void createStats(Stats stats) {
        statsDAO.create(stats);
    }

    public Optional<Stats> findStatsByUser(String username) {
        return statsDAO.findStats(username);
    }

    public List<Stats> getAllStats() {
        return statsDAO.findAllStats();
    }

    public void updateStats(Stats stats) {
        statsDAO.updateStats(stats);
    }

    public void deleteStatsForUser(String username) {
        statsDAO.deleteStats(username);
    }
}
