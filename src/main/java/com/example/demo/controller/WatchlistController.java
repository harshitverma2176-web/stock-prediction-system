package com.example.demo.controller;

import com.example.demo.entity.Watchlist;
import com.example.demo.entity.WatchlistId;
import com.example.demo.repository.WatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private static final Logger logger = LoggerFactory.getLogger(WatchlistController.class);

    private final WatchlistRepository watchlistRepository;

    public WatchlistController(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    @GetMapping
    public List<Watchlist> getAllWatchlists() {
        logger.info("Getting all watchlist items");
        return watchlistRepository.findAll();
    }

    @PostMapping
    public Watchlist createWatchlist(@RequestBody Watchlist watchlist) {
        logger.info("Adding to watchlist");
        return watchlistRepository.save(watchlist);
    }

    @DeleteMapping("/{userId}/{symbol}")
    public String deleteWatchlist(@PathVariable Long userId, @PathVariable String symbol) {
        logger.info("Deleting from watchlist");
        WatchlistId id = new WatchlistId(userId, symbol);
        watchlistRepository.deleteById(id);
        return "Watchlist entry deleted successfully for user " + userId + " and stock " + symbol;
    }
}
