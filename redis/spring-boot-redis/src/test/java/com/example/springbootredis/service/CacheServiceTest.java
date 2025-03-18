package com.example.springbootredis.service;

import com.example.springbootredis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Test
    void cacheDataTest() {
        //given
        User user = new User(1L, "testUser", "test@example.com", LocalDateTime.now());

        //when
        cacheService.cacheData("user:1",user, 60);
        Optional<User> cachedUser = cacheService.getCacheData("user:1", User.class);

        //then
        assertTrue(cachedUser.isPresent());
        assertEquals(user.getUsername(), cachedUser.get().getUsername());
    }


}