package com.example.springbootredis.controller;

import com.example.springbootredis.service.LeaderBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@Slf4j
public class LeaderBoardController {
    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;

    }

    @PostMapping("/scores")
    public ResponseEntity<Void> addSCore(
            @RequestParam String userId,
            @RequestParam double score) {
        leaderBoardService.addScore(userId,score);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/top/{count}")
    public ResponseEntity<List<String>> getTopPlayers(@PathVariable int count) {
        return ResponseEntity.ok(leaderBoardService.getTopPlayers(count));
    }

    @GetMapping("/rank/{userId}")
    public ResponseEntity<Long> getUserRank(@PathVariable String userId){
        Long rank = leaderBoardService.getUserRank(userId);
        return rank != null ? ResponseEntity.ok(rank+1 ) : ResponseEntity.notFound().build();
    }


}
