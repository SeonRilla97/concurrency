package com.example.springbootredis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class LeaderBoardServiceTest {
    @Autowired
    private LeaderBoardService leaderBoardService;

    @Test
    void leaderboardOperationTest() {
        //given
        String userId = "user2";
        double score = 100.0;
        //when
        leaderBoardService.addScore(userId,score);
        List<String> topPlayers = leaderBoardService.getTopPlayers(1);
        Long rank = leaderBoardService.getUserRank(userId);

        //then
        assertFalse(topPlayers.isEmpty());
        assertEquals(userId, topPlayers.get(0));
        assertEquals(0L, rank);
    }
}