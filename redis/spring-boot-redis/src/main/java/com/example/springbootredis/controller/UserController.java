package com.example.springbootredis.controller;

import com.example.springbootredis.model.User;
import com.example.springbootredis.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final CacheService cacheService;

    public UserController(CacheService cacheService) {
        this.cacheService = cacheService;
    }
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return cacheService.getCacheData("user:" + id, User.class)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody  User user) {
        user.setCreatedAt(LocalDateTime.now());
        cacheService.cacheData("user:"+user.getId(), user, 3600);
        return ResponseEntity.ok(user);
    }
}

