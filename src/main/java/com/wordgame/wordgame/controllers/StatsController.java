package com.wordgame.wordgame.controllers;

import com.wordgame.wordgame.domain.Stats;
import com.wordgame.wordgame.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/create")
    public ResponseEntity<Stats> createStats(@RequestBody Stats stats) {
        statsService.createStats(stats);
        return new ResponseEntity<>(stats, HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Stats> getStatsByUser(@PathVariable String username) {
        return statsService.findStatsByUser(username)
                .map(stats -> new ResponseEntity<>(stats, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stats>> getAllStats() {
        List<Stats> allStats = statsService.getAllStats();
        return new ResponseEntity<>(allStats, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Stats> updateStats(@RequestBody Stats stats) {
        statsService.updateStats(stats);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteStats(@PathVariable String username) {
        statsService.deleteStatsForUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
